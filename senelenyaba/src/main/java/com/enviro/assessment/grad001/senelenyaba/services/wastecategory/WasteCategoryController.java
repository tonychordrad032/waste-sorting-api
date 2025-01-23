package com.enviro.assessment.grad001.senelenyaba.services.wastecategory;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/waste-category")
public class WasteCategoryController {
    @Autowired
    private WasteCategoryService wasteCategoryService;

    @PostMapping()
    public ResponseEntity<ResponseResult> saveWasteCategory(@RequestBody WasteCategory wasteCategory){
        String correlationId = UUID.randomUUID().toString();
        return wasteCategoryService.save(wasteCategory, correlationId);
    }

    @GetMapping()
    public ResponseEntity<ResponseResult> listAllWasteCategories(){
        return wasteCategoryService.listAllWasteCategories();
    }

    @PutMapping()
    public ResponseEntity<ResponseResult> updateWasteCategory(@RequestBody WasteCategory wasteCategory){
        String correlationId = UUID.randomUUID().toString();
        return wasteCategoryService.updateWasteCategory(wasteCategory, correlationId);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseResult> deleteWasteCategory(@RequestParam long id){
        String correlationId = UUID.randomUUID().toString();
        return wasteCategoryService.deleteWasteCategory(id, correlationId);
    }
}
