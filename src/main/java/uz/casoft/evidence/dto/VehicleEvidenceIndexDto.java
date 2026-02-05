package uz.casoft.evidence.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleEvidenceIndexDto {

    private Long snapshotId;

    private String plateNumber;

    private String model;

    private Integer productionYear;

    private Integer power;
}
