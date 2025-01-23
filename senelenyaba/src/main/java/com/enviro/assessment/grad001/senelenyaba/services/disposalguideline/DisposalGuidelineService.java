package com.enviro.assessment.grad001.senelenyaba.services.disposalguideline;

import com.enviro.assessment.grad001.senelenyaba.utils.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class DisposalGuidelineService {
    @Autowired
    private DisposalGuidelineRepository disposalGuidelineRepository;

    // Saving disposal guideline
    public ResponseEntity<ResponseResult> saveDisposalGuideline(DisposalGuideline disposalGuideline, String correlationId){
        try {
            log.info("cid => {} Start adding disposal guideline", correlationId);

            DisposalGuideline checkIfExist = disposalGuidelineRepository.findByGuideline(disposalGuideline.getGuideline());

            if (checkIfExist != null){
                log.warn("cid => {} Disposal guideline already exist", correlationId);
                return ResponseEntity.status(409).body(new ResponseResult(409, "Disposal guideline already exist", null));
            }

            disposalGuidelineRepository.save(disposalGuideline);

            log.info("cid => {} Disposal guideline was successfully added", correlationId);
            return ResponseEntity.ok(new ResponseResult(201, "Disposal guideline was successfully added", disposalGuideline));
        }catch (Exception e){
            log.error("cid => {} Error occurred while adding disposal guideline", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }
    }

    // List all disposal guidelines
    public ResponseEntity<ResponseResult> listAllDisposalGuidelines(){
        List<DisposalGuideline> disposalGuidelineList = disposalGuidelineRepository.findAll();
        return ResponseEntity.ok(new ResponseResult(200, "All disposal guideline", disposalGuidelineList));
    }



    // Update the disposal guideline
    public ResponseEntity<ResponseResult> updateDisposalGuideline(DisposalGuideline disposalGuideline, String correlationId){
        try {
            log.info("cid => {} Start updating disposal guideline", correlationId);

            // Fetching the existing disposal guideline to update
            DisposalGuideline updateDisposalGuideline = disposalGuidelineRepository.findById(disposalGuideline.getId()).orElseThrow();

            // Mapping new object with the existing one
            updateDisposalGuideline.setGuideline(disposalGuideline.getGuideline());
            updateDisposalGuideline.setWasteCategory(disposalGuideline.getWasteCategory());

            // Checking if the category name entered does not exist in the database
            DisposalGuideline checkIfExist = disposalGuidelineRepository.findByGuideline(disposalGuideline.getGuideline());

            if(checkIfExist != null){
                log.warn("cid => {} Disposal guideline with name {} already exists", correlationId, disposalGuideline.getGuideline());
                return ResponseEntity.ok(new ResponseResult(409, "Disposal guideline with name  already exists", null));
            }

            // Save changes to the database
            disposalGuidelineRepository.save(updateDisposalGuideline);

            log.info("cid => {} Disposal guideline was successfully updated", correlationId);
            return ResponseEntity.ok(new ResponseResult(200, "Disposal guideline was successfully updated", updateDisposalGuideline));

        }catch (Exception e){
            log.error("cid => {} Error while adding disposal guideline", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400,e.getMessage(), null));
        }finally {
            log.info("cid => {} Finish updating disposal guideline", correlationId);
        }
    }


    // Delete disposal guideline
    public ResponseEntity<ResponseResult> deleteDisposalGuideline(long id, String correlationId){
        try {
            log.info("cid => {} Start deleting disposal guideline", correlationId);
            DisposalGuideline disposalGuideline = disposalGuidelineRepository.findById(id).orElseThrow(() -> new RuntimeException("Disposal guideline not found"));

            if (disposalGuideline == null){
                log.warn("cid => {} Disposal guideline with id {} does not exist", correlationId, id);
                return ResponseEntity.notFound().build();
            }

            disposalGuidelineRepository.delete(disposalGuideline);
            disposalGuidelineRepository.flush();

            log.info("cid => {} Disposal guideline was successfully deleted", correlationId);
            return ResponseEntity.ok(new ResponseResult(200, "Disposal guideline was successfully deleted", null));

        }catch (Exception e){
            log.error("cid => {} Error occurred while deleting disposal guideline", correlationId, e);
            return ResponseEntity.badRequest().body(new ResponseResult(400, e.getMessage(), null));
        }
    }


}
