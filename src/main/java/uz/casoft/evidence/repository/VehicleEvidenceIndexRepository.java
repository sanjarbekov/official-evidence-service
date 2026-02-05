package uz.casoft.evidence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.casoft.evidence.model.VehicleEvidenceIndex;

import java.util.List;

public interface VehicleEvidenceIndexRepository
        extends JpaRepository<VehicleEvidenceIndex, Long> {

    List<VehicleEvidenceIndex> findByPlateNumber(String plateNumber);

    List<VehicleEvidenceIndex> findByModelIgnoreCase(String model);

    List<VehicleEvidenceIndex> findByProductionYear(Integer productionYear);
}
