package uz.casoft.evidence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.casoft.evidence.dto.*;

public interface EvidenceAuditService {

    Page<EvidenceAuditLogDto> findAll(
            Long evidenceId,
            Long snapshotId,
            Pageable pageable
    );

    EvidenceAuditLogDto create(EvidenceAuditLogCreateDto dto);
}
