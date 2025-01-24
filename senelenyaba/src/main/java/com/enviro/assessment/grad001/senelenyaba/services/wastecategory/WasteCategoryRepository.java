package com.enviro.assessment.grad001.senelenyaba.services.wastecategory;

import org.springframework.data.jpa.repository.JpaRepository;

// This is a repository interface for managing WasteCategory entities in the database.
// It extends JpaRepository, which provides built-in CRUD and pagination functionality.

public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {

    // Custom query method to find a WasteCategory entity by its 'name' field.
    // Spring Data JPA automatically generates the implementation based on the method name.
    WasteCategory findByName(String name);
}
