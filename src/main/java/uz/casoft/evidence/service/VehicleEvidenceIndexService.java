package uz.casoft.evidence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.casoft.evidence.dto.*;

public interface VehicleEvidenceIndexService {

    Page<VehicleEvidenceIndexDto> findAll(
            String plateNumber,
            String model,
            Integer productionYear,
            Pageable pageable
    );

    VehicleEvidenceIndexDto findBySnapshotId(Long snapshotId);
}
