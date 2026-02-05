package uz.casoft.evidence.model;

import jakarta.persistence.*;
import lombok.Getter;
import uz.casoft.evidence.model.base.AuditableEntity;
import uz.casoft.evidence.model.enums.EvidenceAction;

@Getter
@Entity
@Table(
        name = "evidence_audit_log",
        indexes = {
                @Index(name = "ix_audit_evidence", columnList = "evidence_id"),
                @Index(name = "ix_audit_snapshot", columnList = "snapshot_id"),
                @Index(name = "ix_audit_action", columnList = "action")
        }
)
public class EvidenceAuditLog extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "evidence_id", nullable = false)
    private Long evidenceId;

    @Column(name = "snapshot_id")
    private Long snapshotId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EvidenceAction action;

    /**
     * User / system / service that triggered the action
     */
    @Column(nullable = false, length = 100)
    private String actor;

    protected EvidenceAuditLog() {
        // JPA only
    }

    public EvidenceAuditLog(
            Long evidenceId,
            Long snapshotId,
            EvidenceAction action,
            String actor
    ) {
        this.evidenceId = evidenceId;
        this.snapshotId = snapshotId;
        this.action = action;
        this.actor = actor;
    }
}
