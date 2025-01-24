package com.enviro.assessment.grad001.senelenyaba.services.recyclingtip;

import org.springframework.data.jpa.repository.JpaRepository;
// This is similar to the code in WasteCategoryRepository. Refer to that class for detailed comments.

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    RecyclingTip findByTip(String tip);
}
