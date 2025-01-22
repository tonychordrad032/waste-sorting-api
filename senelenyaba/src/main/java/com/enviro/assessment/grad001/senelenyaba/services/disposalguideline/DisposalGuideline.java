package com.enviro.assessment.grad001.senelenyaba.services.disposalguideline;

import com.enviro.assessment.grad001.senelenyaba.services.wastecategory.WasteCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "tbl_disposal_guidelines")
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Guideline cannot be empty")
    private String guideline;

    @ManyToOne
    @JoinColumn(name = "waste_category_id")
    private WasteCategory wasteCategory;
}
