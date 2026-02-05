package uz.casoft.evidence.dto;

import lombok.*;
import uz.casoft.evidence.model.enums.EvidenceSource;
import uz.casoft.evidence.model.enums.EvidenceType;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialEvidenceDto {

    private Long id;

    private EvidenceType type;

    private EvidenceSource source;

    /**
     * Subject reference:
     *  - vehicle plate
     *  - cadastre number
     *  - masked PINFL / INN
     */
    private String subjectRef;

    /* ===== Auditing (from AuditableEntity) ===== */

    private Instant createdDate;

    private Instant lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;
}
