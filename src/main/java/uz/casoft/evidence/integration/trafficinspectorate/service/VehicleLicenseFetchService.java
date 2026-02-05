package uz.casoft.evidence.integration.trafficinspectorate.service;

import uz.casoft.evidence.dto.OfficialEvidenceSnapshotDto;
import uz.casoft.evidence.integration.trafficinspectorate.dto.VehicleLicenseRequestDto;

public interface VehicleLicenseFetchService {

    OfficialEvidenceSnapshotDto fetchAndStore(
            VehicleLicenseRequestDto request
    );
}
