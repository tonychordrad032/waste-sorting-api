package com.enviro.assessment.grad001.senelenyaba.services.disposalguideline;

import com.enviro.assessment.grad001.senelenyaba.services.wastecategory.WasteCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
// This is similar to the WasteCategory class. Refer to that class for detailed comments.

@Data
@Entity(name = "tbl_disposal_guidelines")
public class DisposalGuideline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Guideline cannot be empty")
    private String guideline;

    @ManyToOne // Defines a many-to-one relationship with the WasteCategory entity.
    @JoinColumn(name = "waste_category_id")
    // Maps this field to the foreign key column 'waste_category_id' in the "tbl_disposal_guidelines" table.
    private WasteCategory wasteCategory; // Links this guideline to a specific waste category.
}

