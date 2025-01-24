package com.enviro.assessment.grad001.senelenyaba.services.wastecategory;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// This is the controller layer responsible for handling HTTP requests related to waste categories.
// It maps incoming API requests to the appropriate service layer methods.

@RestController // Marks this class as a REST controller, enabling it to handle HTTP requests and return JSON responses.
@RequestMapping("/api/waste-category") // Sets the base path for all endpoints in this controller.
public class WasteCategoryController {

    @Autowired // Automatically injects an instance of WasteCategoryService into this controller.
    private WasteCategoryService wasteCategoryService;

    /**
     * Endpoint to save a new waste category.
     * HTTP Method: POST
     * URL: /api/waste-category
     * Request Body: A JSON object representing a WasteCategory.
     * Response: A ResponseEntity containing the result of the save operation.
     */
    @PostMapping()
    public ResponseEntity<ResponseResult> saveWasteCategory(@RequestBody WasteCategory wasteCategory) {
        // Generate a unique correlation ID for tracking this operation.
        String correlationId = UUID.randomUUID().toString();

        // Call the service layer to save the waste category.
        return wasteCategoryService.save(wasteCategory, correlationId);
    }

    /**
     * Endpoint to list all waste categories.
     * HTTP Method: GET
     * URL: /api/waste-category
     * Response: A ResponseEntity containing a list of all waste categories.
     */
    @GetMapping()
    public ResponseEntity<ResponseResult> listAllWasteCategories() {
        // Call the service layer to retrieve all waste categories.
        return wasteCategoryService.listAllWasteCategories();
    }

    /**
     * Endpoint to update an existing waste category.
     * HTTP Method: PUT
     * URL: /api/waste-category
     * Request Body: A JSON object representing the updated WasteCategory.
     * Response: A ResponseEntity containing the result of the update operation.
     */
    @PutMapping()
    public ResponseEntity<ResponseResult> updateWasteCategory(@RequestBody WasteCategory wasteCategory) {
        // Generate a unique correlation ID for tracking this operation.
        String correlationId = UUID.randomUUID().toString();

        // Call the service layer to update the waste category.
        return wasteCategoryService.updateWasteCategory(wasteCategory, correlationId);
    }

    /**
     * Endpoint to delete a waste category by its ID.
     * HTTP Method: DELETE
     * URL: /api/waste-category
     * Query Parameter: 'id' (the ID of the waste category to be deleted).
     * Response: A ResponseEntity containing the result of the delete operation.
     */
    @DeleteMapping()
    public ResponseEntity<ResponseResult> deleteWasteCategory(@RequestParam long id) {
        // Generate a unique correlation ID for tracking this operation.
        String correlationId = UUID.randomUUID().toString();

        // Call the service layer to delete the waste category.
        return wasteCategoryService.deleteWasteCategory(id, correlationId);
    }
}
