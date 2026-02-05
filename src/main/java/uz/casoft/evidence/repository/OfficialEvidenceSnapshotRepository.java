package uz.casoft.evidence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.casoft.evidence.model.OfficialEvidenceSnapshot;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface OfficialEvidenceSnapshotRepository
        extends JpaRepository<OfficialEvidenceSnapshot, Long> {

    Optional<OfficialEvidenceSnapshot>
    findTopByEvidenceIdOrderByFetchedAtDesc(Long evidenceId);

    Page<OfficialEvidenceSnapshot>
    findAllByEvidenceIdOrderByFetchedAtAsc(
            Long evidenceId,
            Pageable pageable
    );

    Optional<OfficialEvidenceSnapshot>
    findByRequestId(String requestId);

    @Query("""
        select s from OfficialEvidenceSnapshot s
        where s.evidence.id = :evidenceId
          and s.fetchedAt <= :moment
        order by s.fetchedAt desc
    """)
    Optional<OfficialEvidenceSnapshot>
    findSnapshotAsOf(Long evidenceId, Instant moment);
}
