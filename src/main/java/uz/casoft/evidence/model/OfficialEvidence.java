package uz.casoft.evidence.model;

import jakarta.persistence.*;
import lombok.Getter;
import uz.casoft.evidence.model.base.AuditableEntity;
import uz.casoft.evidence.model.enums.EvidenceSource;
import uz.casoft.evidence.model.enums.EvidenceType;

@Getter
@Entity
@Table(
        name = "official_evidence",
        indexes = {
                @Index(name = "ix_official_evidence_subject_ref", columnList = "subject_ref"),
                @Index(name = "ix_official_evidence_type", columnList = "type"),
                @Index(name = "ix_official_evidence_source", columnList = "source")
        }
)
public class OfficialEvidence extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Evidence type (VEHICLE, REAL_ESTATE, PERSON, etc.)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EvidenceType type;

    /**
     * Official data source (GAI, CADASTRE, TAX, etc.)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EvidenceSource source;

    /**
     * Subject reference:
     *  - vehicle plate number
     *  - cadastre number
     *  - masked PINFL / INN
     */
    @Column(name = "subject_ref", nullable = false, length = 100)
    private String subjectRef;

    protected OfficialEvidence() {
        // JPA only
    }

    public OfficialEvidence(
            EvidenceType type,
            EvidenceSource source,
            String subjectRef
    ) {
        this.type = type;
        this.source = source;
        this.subjectRef = subjectRef;
    }
}
