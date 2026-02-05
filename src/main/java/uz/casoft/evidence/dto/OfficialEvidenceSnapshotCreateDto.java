package uz.casoft.evidence.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialEvidenceSnapshotCreateDto {

    private Long evidenceId;

    /**
     * FULL raw JSON returned by official source.
     * Stored unchanged — legal evidence.
     */
    private String rawPayload;

    /**
     * External request identifier (GAI request ID)
     */
    private String requestId;

    /**
     * Valid date of information (if source provides it)
     */
    private LocalDate validAt;
}
