package com.enviro.assessment.grad001.senelenyaba.services.wastecategory;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class WasteCategoryService {
    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    // Add waste category
    public ResponseEntity<ResponseResult> save(WasteCategory wasteCategory, String correlationId){
        try {
            log.info("cid => {} Start adding waste category", correlationId);
            WasteCategory checkIfExist = wasteCategoryRepository.findByName(wasteCategory.getName());

            if(checkIfExist != null){
                log.warn("cid => {} Waste category with name {} already exists", correlationId, wasteCategory.getName());
                return ResponseEntity.ok(new ResponseResult(409, "Waste category with name : " + wasteCategory.getName() + ", already exists", null));
            }

            wasteCategoryRepository.save(wasteCategory);
            log.info("cid => {} Waste category was successfully added");

            return ResponseEntity.ok(new ResponseResult(201, "Waste category was successfully added", wasteCategory));
        }catch (Exception e){
            log.error("cid => {} Error while adding waste category", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400,e.getMessage(), null));
        }finally {
            log.info("cid => {} Finish adding category", correlationId);
        }
    }

    // List all waste categories
    public ResponseEntity<ResponseResult> listAllWasteCategories(){
        log.info("Start Listing waste categories");
        List<WasteCategory> categoryList = wasteCategoryRepository.findAll();
        log.info("Finish Listing waste categories");
        return ResponseEntity.ok(new ResponseResult(200, "All waste categories", categoryList));
    }



    // Update the waster category
    public ResponseEntity<ResponseResult> updateWasteCategory(WasteCategory wasteCategory, String correlationId){
        try {
            log.info("cid => {} Start updating waste category", correlationId);

            // Fetching the existing waste category to update
            WasteCategory updateWasteCategory = wasteCategoryRepository.findById(wasteCategory.getId()).orElseThrow();

            // Mapping new object with the existing one
            updateWasteCategory.setName(wasteCategory.getName());
            updateWasteCategory.setDescription(wasteCategory.getDescription());

            // Checking if the category name entered does not exist in the database
            WasteCategory checkIfExist = wasteCategoryRepository.findByName(wasteCategory.getName());

            if(checkIfExist != null){
                log.warn("cid => {} Waste category with name {} already exists", correlationId, wasteCategory.getName());
                return ResponseEntity.ok(new ResponseResult(409, "Waste category with name {} already exists", null));
            }

            // Save changes to the database
            wasteCategoryRepository.save(updateWasteCategory);

            log.info("cid => {} Waste category was successfully updated", correlationId);
            return ResponseEntity.ok(new ResponseResult(200, "Waste category was successfully updated", updateWasteCategory));

        }catch (Exception e){
            log.error("cid => {} Error while adding waste category", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400,e.getMessage(), null));
        }finally {
            log.info("cid => {} Finish updating waste category", correlationId);
        }
    }


    // Delete waste category
    public ResponseEntity<ResponseResult> deleteWasteCategory(long id, String correlationId){
        try {
            log.info("cid => {} Start deleting waste category", correlationId);
            WasteCategory wasteCategory = wasteCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Waste category not found"));

            if (wasteCategory == null){
                log.warn("cid => {} Waste Category with id {} does not exist", correlationId, id);
                return ResponseEntity.notFound().build();
            }

            wasteCategoryRepository.delete(wasteCategory);
            wasteCategoryRepository.flush();

            log.info("cid => {} Waste category was successfully deleted", correlationId);
            return ResponseEntity.ok(new ResponseResult(200, "Waste category was successfully deleted", null));

        }catch (Exception e){
            log.error("cid => {} Error occurred while deleting waste category", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }finally {
            log.info("cid => {} Finish deleting waste category");
        }
    }
}
