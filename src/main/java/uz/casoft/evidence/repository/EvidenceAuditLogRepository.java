package uz.casoft.evidence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.casoft.evidence.model.EvidenceAuditLog;
import uz.casoft.evidence.model.enums.EvidenceAction;

import java.util.List;

public interface EvidenceAuditLogRepository
        extends JpaRepository<EvidenceAuditLog, Long> {

    List<EvidenceAuditLog> findAllByEvidenceId(Long evidenceId);

    List<EvidenceAuditLog> findAllBySnapshotId(Long snapshotId);

    List<EvidenceAuditLog> findAllByAction(EvidenceAction action);
}
