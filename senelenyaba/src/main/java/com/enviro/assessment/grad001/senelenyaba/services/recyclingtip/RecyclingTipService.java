package com.enviro.assessment.grad001.senelenyaba.services.recyclingtip;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
// This is similar to the logic in WasteCategoryService. Refer to that class for detailed comments.

@Log4j2
@Service
public class RecyclingTipService {

    @Autowired
    private RecyclingTipRepository recyclingTipRepository;

    // Saving recycling tip
    public ResponseEntity<ResponseResult> saveRecyclingTip(RecyclingTip recyclingTip, String correlationId){
        try {
            log.info("cid => {} Start adding recycling tip", correlationId);

            RecyclingTip checkIfExist = recyclingTipRepository.findByTip(recyclingTip.getTip());

            if (checkIfExist != null){
                log.warn("cid => {} Recycling tip already exist", correlationId);
                return ResponseEntity.status(409).body(new ResponseResult(409, "Recycling tip already exist", null));
            }

            recyclingTipRepository.save(recyclingTip);

            log.info("cid => {} Recycling tip was successfully added", correlationId);
            return ResponseEntity.ok(new ResponseResult(201, "Recycling tip was successfully added", recyclingTip));
        }catch (Exception e){
            log.error("cid => {} Error occurred while adding recycling tip", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }
    }

    // List all recycling tips
    public ResponseEntity<ResponseResult> listAllRecyclingTip(){
        List<RecyclingTip> recyclingTipList = recyclingTipRepository.findAll();
        return ResponseEntity.ok(new ResponseResult(200, "Listing all recycling tips", recyclingTipList));
    }

    // Updating recycling tip
    public ResponseEntity<ResponseResult> updateRecyclingTip(RecyclingTip recyclingTip, String correlationId){
        try {
            log.info("cid => {} Start updating recycling tip", correlationId);
            RecyclingTip updateRecyclingTip = recyclingTipRepository.findById(recyclingTip.getId()).orElseThrow(() -> new RuntimeException("Recycling tip with id: "+recyclingTip.getId()+" is not found"));

            updateRecyclingTip.setTip(recyclingTip.getTip());
            updateRecyclingTip.setWasteCategory(recyclingTip.getWasteCategory());

            RecyclingTip checkIfExist = recyclingTipRepository.findByTip(recyclingTip.getTip());
            if (checkIfExist != null){
                log.warn("cid => {} Recycling tip already exist", correlationId);
                return ResponseEntity.status(409).body(new ResponseResult(409, "Recycling tip already exist", null));
            }

            recyclingTipRepository.save(updateRecyclingTip);
            log.info("cid => {} Recycling tip was successfully updated", correlationId);
            return ResponseEntity.ok(new ResponseResult(201, "Recycling tip was successfully updated", updateRecyclingTip));
        }catch (Exception e){
            log.error("cid => {} Error occurred while updating recycling tip", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }
    }

    // Delete recycling tip
    public ResponseEntity<ResponseResult> deleteRecyclingTip(long id, String correlationId){
        try {
            log.info("cid => {} Start deleting recycling tip", correlationId);
            RecyclingTip recyclingTip = recyclingTipRepository.findById(id).orElseThrow(() -> new RuntimeException("Recycling tip not found"));

            if (recyclingTip == null){
                log.warn("cid => {} Recycling tip with id {} does not exist", correlationId, id);
                return ResponseEntity.notFound().build();
            }

            recyclingTipRepository.delete(recyclingTip);
            recyclingTipRepository.flush(); // Refreshing the database

            log.info("cid => {} Recycling tip was successfully deleted", correlationId);
            return ResponseEntity.ok(new ResponseResult(200, "Recycling tip was successfully deleted", null));

        }catch (Exception e){
            log.error("cid => {} Error occurred while deleting recycling tip", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }
    }
}
