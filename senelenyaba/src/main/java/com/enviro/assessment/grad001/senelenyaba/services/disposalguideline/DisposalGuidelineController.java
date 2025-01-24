package com.enviro.assessment.grad001.senelenyaba.services.disposalguideline;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
// This is similar to the logic in WasteCategoryController. Refer to that class for detailed comments.


@RestController
@RequestMapping("/api/disposal-guideline")
public class DisposalGuidelineController {
    @Autowired
    private DisposalGuidelineService disposalGuidelineService; // This will allow me to access the methods

    @PostMapping
    public ResponseEntity<ResponseResult> saveDisposalGuideline(@RequestBody DisposalGuideline disposalGuideline){
        String correlationId = UUID.randomUUID().toString();
        return disposalGuidelineService.saveDisposalGuideline(disposalGuideline, correlationId);
    }

    @GetMapping
    public ResponseEntity<ResponseResult> listAllDisposalGuidelines(){
        return disposalGuidelineService.listAllDisposalGuidelines();
    }

    @PutMapping
    public ResponseEntity<ResponseResult> updateDisposalGuideline(@RequestBody DisposalGuideline disposalGuideline){
        String correlationId = UUID.randomUUID().toString();
        return disposalGuidelineService.updateDisposalGuideline(disposalGuideline, correlationId);
    }

    @DeleteMapping
    public ResponseEntity<ResponseResult> deleteDisposalGuideline(@RequestParam long id){
        String correlationId = UUID.randomUUID().toString();
        return disposalGuidelineService.deleteDisposalGuideline(id, correlationId);
    }
}
