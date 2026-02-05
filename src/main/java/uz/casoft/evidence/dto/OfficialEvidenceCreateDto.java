package uz.casoft.evidence.dto;

import lombok.*;
import uz.casoft.evidence.model.enums.EvidenceSource;
import uz.casoft.evidence.model.enums.EvidenceType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialEvidenceCreateDto {

    private EvidenceType type;

    private EvidenceSource source;

    /**
     * Subject identifier
     */
    private String subjectRef;
}
