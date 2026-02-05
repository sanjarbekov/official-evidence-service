package uz.casoft.evidence.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialEvidenceSnapshotDto {

    private Long id;

    private Long evidenceId;

    /**
     * When data was fetched from official source (GAI)
     */
    private Instant fetchedAt;

    /**
     * Date for which data is valid (if provided by source)
     */
    private LocalDate validAt;

    /**
     * External request identifier (GAI request ID)
     */
    private String requestId;

    /**
     * SHA-256 checksum of raw payload
     */
    private String checksum;

    /* ===== Auditing ===== */

    private Instant createdDate;
}
