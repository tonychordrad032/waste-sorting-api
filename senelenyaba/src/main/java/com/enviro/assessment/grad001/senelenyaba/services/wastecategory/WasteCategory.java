package com.enviro.assessment.grad001.senelenyaba.services.wastecategory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // Lombok's annotation that generates boilerplate code such as getters, setters, equals, hashCode, and toString methods.
@Entity(name = "tbl_waste_categories") // Specifies that this class is a JPA entity and maps it to the "tbl_categories" table in the database.
public class WasteCategory {

    @Id // Marks this field as the primary key of the table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies that the primary key values will be generated automatically by the database (e.g., MySQL's AUTO_INCREMENT).
    private long id;

    @NotBlank(message = "Name cannot be empty")
    // Validates that the "name" field cannot be null, empty, or contain only whitespace.
    // If validation fails, the provided message will be used in the error response.
    private String name;

    private String description;
    // Optional field to provide additional details about the waste category.
    // This field is not validated, so it can be left empty or null.
}
