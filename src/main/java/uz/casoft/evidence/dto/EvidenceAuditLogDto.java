package uz.casoft.evidence.dto;

import lombok.*;
import uz.casoft.evidence.model.enums.EvidenceAction;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvidenceAuditLogDto {

    private Long id;

    private Long evidenceId;

    private Long snapshotId;

    private EvidenceAction action;

    /**
     * User / system / service
     */
    private String actor;

    private Instant createdDate;
}
