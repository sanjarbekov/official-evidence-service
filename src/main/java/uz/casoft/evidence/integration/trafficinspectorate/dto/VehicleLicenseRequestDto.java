package uz.casoft.evidence.integration.trafficinspectorate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleLicenseRequestDto {

    /**
     * Unique request identifier (UUID recommended)
     */
    @JsonProperty("pRequestID")
    private String requestId;

    /**
     * Vehicle registration certificate series
     */
    @JsonProperty("pTexpassportSery")
    private String registrationCertSeries;

    /**
     * Vehicle registration certificate number
     */
    @JsonProperty("pTexpassportNumber")
    private String registrationCertNumber;

    /**
     * State license plate number
     */
    @JsonProperty("pPlateNumber")
    private String plateNumber;
}
