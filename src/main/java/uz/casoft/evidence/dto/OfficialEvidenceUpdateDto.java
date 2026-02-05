package uz.casoft.evidence.dto;

import lombok.*;

/**
 * Administrative correction only.
 * DOES NOT affect legal meaning of evidence.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficialEvidenceUpdateDto {

    /**
     * Example:
     *  - plate formatting fix
     *  - masked identifier correction
     */
    private String subjectRef;
}
