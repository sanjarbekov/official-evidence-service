package uz.casoft.evidence.model;

import jakarta.persistence.*;
import lombok.Getter;
import uz.casoft.evidence.model.base.AuditableEntity;

@Getter
@Entity
@Table(
        name = "vehicle_evidence_index",
        indexes = {
                @Index(name = "ix_vehicle_index_plate", columnList = "plate_number"),
                @Index(name = "ix_vehicle_index_snapshot", columnList = "snapshot_id")
        }
)
public class VehicleEvidenceIndex extends AuditableEntity {

    @Id
    @Column(name = "snapshot_id")
    private Long snapshotId;

    @Column(name = "plate_number", length = 20)
    private String plateNumber;

    @Column(length = 100)
    private String model;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column
    private Integer power;

    protected VehicleEvidenceIndex() {
        // JPA only
    }

    public VehicleEvidenceIndex(
            Long snapshotId,
            String plateNumber,
            String model,
            Integer productionYear,
            Integer power
    ) {
        this.snapshotId = snapshotId;
        this.plateNumber = plateNumber;
        this.model = model;
        this.productionYear = productionYear;
        this.power = power;
    }
}
