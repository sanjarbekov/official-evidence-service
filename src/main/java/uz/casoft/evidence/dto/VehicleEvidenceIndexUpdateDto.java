package uz.casoft.evidence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEvidenceIndexUpdateDto {

    private String plateNumber;

    private String model;

    private Integer productionYear;

    private Integer power;
}
