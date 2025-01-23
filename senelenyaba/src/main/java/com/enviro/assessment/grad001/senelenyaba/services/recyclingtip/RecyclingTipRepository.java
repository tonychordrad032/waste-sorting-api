package com.enviro.assessment.grad001.senelenyaba.services.recyclingtip;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    RecyclingTip findByTip(String tip);
}
