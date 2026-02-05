package uz.casoft.evidence.integration.trafficinspectorate.client;

import uz.casoft.evidence.integration.trafficinspectorate.dto.VehicleLicenseRequestDto;

public interface VehicleLicenseClient {

    /**
     * Calls State Traffic Inspectorate API
     * and returns RAW JSON response.
     */
    String fetchRaw(VehicleLicenseRequestDto request);
}
