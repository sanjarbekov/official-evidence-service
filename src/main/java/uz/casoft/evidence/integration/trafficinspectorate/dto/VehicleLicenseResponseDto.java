package uz.casoft.evidence.integration.trafficinspectorate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleLicenseResponseDto {

    /**
     * Result code: 1 = success
     */
    @JsonProperty("pResult")
    private Integer resultCode;

    @JsonProperty("pComment")
    private String resultMessage;

    /**
     * Owner PINFL (individual)
     */
    @JsonProperty("pPinpp")
    private String pinfl;

    /**
     * Owner full name
     */
    @JsonProperty("pOwner")
    private String ownerName;

    /**
     * 0 = individual, 1 = legal entity
     */
    @JsonProperty("pOwnerType")
    private Integer ownerType;

    /**
     * INN if owner is legal entity
     */
    @JsonProperty("pOrganizationInn")
    private String organizationInn;

    @JsonProperty("Vehicle")
    private List<VehicleDto> vehicles;
}
