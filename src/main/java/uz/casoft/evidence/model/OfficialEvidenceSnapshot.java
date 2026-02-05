package uz.casoft.evidence.model;

import jakarta.persistence.*;
import lombok.Getter;
import uz.casoft.evidence.model.base.AuditableEntity;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Entity
@Table(
        name = "official_evidence_snapshot",
        indexes = {
                @Index(name = "ix_snapshot_evidence", columnList = "evidence_id"),
                @Index(name = "ix_snapshot_fetched_at", columnList = "fetched_at"),
                @Index(name = "ix_snapshot_request_id", columnList = "request_id")
        }
)
public class OfficialEvidenceSnapshot extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Logical evidence container
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evidence_id", nullable = false)
    private OfficialEvidence evidence;

    /**
     * When data was fetched from the official source (GAI)
     */
    @Column(name = "fetched_at", nullable = false, updatable = false)
    private Instant fetchedAt;

    /**
     * Date for which data is valid (if source provides it)
     */
    @Column(name = "valid_at")
    private LocalDate validAt;

    /**
     * External request identifier (GAI request ID)
     */
    @Column(name = "request_id", length = 64)
    private String requestId;

    /**
     * FULL raw JSON payload returned by official source
     * Stored unchanged — legal evidence
     */
    @Column(name = "raw_payload", nullable = false)
    private String rawPayload;

    /**
     * SHA-256 checksum of rawPayload
     */
    @Column(nullable = false, length = 64)
    private String checksum;

    protected OfficialEvidenceSnapshot() {
        // JPA only
    }

    public OfficialEvidenceSnapshot(
            OfficialEvidence evidence,
            Instant fetchedAt,
            LocalDate validAt,
            String requestId,
            String rawPayload,
            String checksum
    ) {
        this.evidence = evidence;
        this.fetchedAt = fetchedAt;
        this.validAt = validAt;
        this.requestId = requestId;
        this.rawPayload = rawPayload;
        this.checksum = checksum;
    }
}
