package com.enviro.assessment.grad001.senelenyaba.services.wastecategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteCategoryRepository extends JpaRepository<WasteCategory,Long> {
    WasteCategory findByName(String name);
}
