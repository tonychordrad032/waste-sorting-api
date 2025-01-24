package com.enviro.assessment.grad001.senelenyaba.services.recyclingtip;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
// This is similar to the logic in WasteCategoryController. Refer to that class for detailed comments.

@RestController
@RequestMapping("/api/recycling-tip")
public class RecyclingTipController {
    @Autowired
    private RecyclingTipService recyclingTipService;

    @PostMapping
    public ResponseEntity<ResponseResult> saveRecyclingTip(@RequestBody RecyclingTip recyclingTip){
        String correlationId = UUID.randomUUID().toString();
        return recyclingTipService.saveRecyclingTip(recyclingTip, correlationId);
    }

    @GetMapping
    public ResponseEntity<ResponseResult> getAllRecyclingTips(){
        return recyclingTipService.listAllRecyclingTip();
    }

    @PutMapping
    public ResponseEntity<ResponseResult> updateRecyclingTip(@RequestBody RecyclingTip recyclingTip){
        String correlationId = UUID.randomUUID().toString();
        return recyclingTipService.updateRecyclingTip(recyclingTip, correlationId);
    }

    @DeleteMapping
    public ResponseEntity<ResponseResult> deleteRecyclingTip(@RequestParam long id){
        String correlationId = UUID.randomUUID().toString();
        return recyclingTipService.deleteRecyclingTip(id, correlationId);
    }

}
