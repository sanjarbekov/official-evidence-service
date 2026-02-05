package uz.casoft.evidence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.casoft.evidence.dto.*;
import uz.casoft.evidence.model.enums.EvidenceSource;
import uz.casoft.evidence.model.enums.EvidenceType;

public interface OfficialEvidenceService {

    Page<OfficialEvidenceDto> findAll(
            EvidenceType type,
            EvidenceSource source,
            Pageable pageable
    );

    Page<OfficialEvidenceDto> search(
            String query,
            EvidenceType type,
            EvidenceSource source,
            Pageable pageable
    );

    OfficialEvidenceDto findById(Long id);

    OfficialEvidenceDto getOrCreate(OfficialEvidenceCreateDto dto);
}
