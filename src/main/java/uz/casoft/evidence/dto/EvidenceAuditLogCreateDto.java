package uz.casoft.evidence.dto;

import lombok.*;
import uz.casoft.evidence.model.enums.EvidenceAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvidenceAuditLogCreateDto {

    private Long evidenceId;

    private Long snapshotId;

    private EvidenceAction action;

    private String actor;
}
