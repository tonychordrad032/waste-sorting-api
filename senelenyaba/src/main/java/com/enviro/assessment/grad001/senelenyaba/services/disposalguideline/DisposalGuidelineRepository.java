package com.enviro.assessment.grad001.senelenyaba.services.disposalguideline;

import org.springframework.data.jpa.repository.JpaRepository;
// This is similar to the code in WasteCategoryRepository. Refer to that class for detailed comments.

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    DisposalGuideline findByGuideline(String guideline);
}
