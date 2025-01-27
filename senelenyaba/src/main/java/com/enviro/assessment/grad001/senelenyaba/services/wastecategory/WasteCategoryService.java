package com.enviro.assessment.grad001.senelenyaba.services.wastecategory;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2 // This annotation enables logging capabilities using Log4j2, allowing you to log various events in your application.
@Service // Marks this class as a Spring Service component, enabling dependency injection and service-level business logic.
public class WasteCategoryService {

    @Autowired // Automatically injects an instance of WasteCategoryRepository into this service class.
    private WasteCategoryRepository wasteCategoryRepository;

    // Add waste category
    public ResponseEntity<ResponseResult> save(WasteCategory wasteCategory, String correlationId) {
        try {
            log.info("cid => {} Start adding waste category", correlationId); // Log the start of the save operation.

            // Check if a waste category with the same name already exists in the database.
            WasteCategory checkIfExist = wasteCategoryRepository.findByName(wasteCategory.getName());
            if (checkIfExist != null) {
                log.warn("cid => {} Waste category with name {} already exists", correlationId, wasteCategory.getName()); // Log a warning if a duplicate category is found.
                return ResponseEntity.ok(new ResponseResult(409, "Waste category with name : " + wasteCategory.getName() + ", already exists", null)); // Return a 409 Conflict response.
            }

            // Save the new waste category to the database.
            wasteCategoryRepository.save(wasteCategory);
            log.info("cid => {} Waste category was successfully added"); // Log successful addition.

            return ResponseEntity.ok(new ResponseResult(201, "Waste category was successfully added", wasteCategory)); // Return a 201 Created response.
        } catch (Exception e) {
            log.error("cid => {} Error while adding waste category", correlationId, e); // Log an error if an exception occurs.
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null)); // Return a 400 Bad Request response with error details.
        } finally {
            log.info("cid => {} Finish adding category", correlationId); // Log the end of the save operation.
        }
    }

    // List all waste categories
    public ResponseEntity<ResponseResult> listAllWasteCategories() {
        log.info("Start Listing waste categories"); // Log the start of the list operation.

        // Retrieve all waste categories from the database.
        List<WasteCategory> categoryList = wasteCategoryRepository.findAll();

        log.info("Finish Listing waste categories"); // Log the end of the list operation.
        return ResponseEntity.ok(new ResponseResult(200, "All waste categories", categoryList)); // Return a 200 OK response with the list of categories.
    }

    // Update the waste category
    public ResponseEntity<ResponseResult> updateWasteCategory(WasteCategory wasteCategory, String correlationId) {
        try {
            log.info("cid => {} Start updating waste category", correlationId); // Log the start of the update operation.


            // Check if the updated category name already exists.
            WasteCategory checkIfExist = wasteCategoryRepository.findByName(wasteCategory.getName());
            if (checkIfExist != null) {
                log.warn("cid => {} Waste category with name {} already exists", correlationId, wasteCategory.getName()); // Log a warning if a duplicate name is found.
                return ResponseEntity.ok(new ResponseResult(409, "Waste category with name {} already exists", null)); // Return a 409 Conflict response.
            }

            // Fetch the existing waste category to update.
            WasteCategory updateWasteCategory = wasteCategoryRepository.findById(wasteCategory.getId())
                    .orElseThrow(); // Throws an exception if the category does not exist.

            // Map new values to the existing waste category.
            updateWasteCategory.setName(wasteCategory.getName());
            updateWasteCategory.setDescription(wasteCategory.getDescription());


            // Save the updated waste category to the database.
            wasteCategoryRepository.save(updateWasteCategory);
            log.info("cid => {} Waste category was successfully updated", correlationId); // Log successful update.

            return ResponseEntity.ok(new ResponseResult(200, "Waste category was successfully updated", updateWasteCategory)); // Return a 200 OK response.
        } catch (Exception e) {
            log.error("cid => {} Error while adding waste category", correlationId, e); // Log an error if an exception occurs.
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null)); // Return a 400 Bad Request response.
        } finally {
            log.info("cid => {} Finish updating waste category", correlationId); // Log the end of the update operation.
        }
    }

    // Delete waste category
    public ResponseEntity<ResponseResult> deleteWasteCategory(long id, String correlationId) {
        try {
            log.info("cid => {} Start deleting waste category", correlationId); // Log the start of the delete operation.

            // Find the waste category by its ID.
            WasteCategory wasteCategory = wasteCategoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Waste category not found")); // Throw an exception if the category does not exist.

            // Delete the waste category from the database.
            wasteCategoryRepository.delete(wasteCategory);
            wasteCategoryRepository.flush(); // Flush pending changes to the database.

            log.info("cid => {} Waste category was successfully deleted", correlationId); // Log successful deletion.
            return ResponseEntity.ok(new ResponseResult(200, "Waste category was successfully deleted", null)); // Return a 200 OK response.
        } catch (Exception e) {
            log.error("cid => {} Error occurred while deleting waste category", correlationId, e); // Log an error if an exception occurs.
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null)); // Return a 400 Bad Request response.
        } finally {
            log.info("cid => {} Finish deleting waste category"); // Log the end of the delete operation.
        }
    }
}

