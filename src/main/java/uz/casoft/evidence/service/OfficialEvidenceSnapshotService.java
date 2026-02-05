package uz.casoft.evidence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.casoft.evidence.dto.OfficialEvidenceSnapshotCreateDto;
import uz.casoft.evidence.dto.OfficialEvidenceSnapshotDto;

public interface OfficialEvidenceSnapshotService {

    Page<OfficialEvidenceSnapshotDto> findAll(
            Long evidenceId,
            Pageable pageable
    );

    OfficialEvidenceSnapshotDto findById(Long id);

    /**
     * Latest snapshot (by fetchedAt desc)
     */
    OfficialEvidenceSnapshotDto findLatest(Long evidenceId);

    /**
     * Append-only creation
     */
    OfficialEvidenceSnapshotDto create(
            OfficialEvidenceSnapshotCreateDto dto
    );
}
