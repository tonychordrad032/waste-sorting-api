package com.enviro.assessment.grad001.senelenyaba.services.recyclingtip;

import com.enviro.assessment.grad001.senelenyaba.services.wastecategory.WasteCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
// This is similar to the code in DisposalGuideline. Refer to that class for detailed comments.

@Data
@Entity(name = "tbl_recycling_tips")
public class RecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tip cannot be empty")
    private String tip;

    @ManyToOne
    @JoinColumn(name = "waste_category_id", nullable = true)
    private WasteCategory wasteCategory;
}
