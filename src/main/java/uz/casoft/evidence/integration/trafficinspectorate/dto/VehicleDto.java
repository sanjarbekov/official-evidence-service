package uz.casoft.evidence.integration.trafficinspectorate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

    @JsonProperty("pVehicleId")
    private Long vehicleId;

    @JsonProperty("pPlateNumber")
    private String plateNumber;

    @JsonProperty("pModel")
    private String model;

    @JsonProperty("pVehicleColor")
    private String color;

    @JsonProperty("pRegistrationDate")
    private String registrationDate; // dd.MM.yyyy

    @JsonProperty("pDivision")
    private String registrationAuthority;

    @JsonProperty("pYear")
    private Integer manufactureYear;

    @JsonProperty("pVehicleType")
    private Integer vehicleTypeCode;

    @JsonProperty("pKuzov")
    private String vin;

    @JsonProperty("pShassi")
    private String chassisNumber;

    @JsonProperty("pMotor")
    private String engineNumber;

    @JsonProperty("pFuelType")
    private Integer fuelTypeCode;

    @JsonProperty("pPower")
    private Integer enginePowerHp;
}
