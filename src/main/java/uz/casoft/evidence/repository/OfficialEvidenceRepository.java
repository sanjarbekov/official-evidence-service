package uz.casoft.evidence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.casoft.evidence.model.OfficialEvidence;
import uz.casoft.evidence.model.enums.EvidenceSource;
import uz.casoft.evidence.model.enums.EvidenceType;

import java.util.Optional;

@Repository
public interface OfficialEvidenceRepository
        extends JpaRepository<OfficialEvidence, Long> {

    /* =========================
       BASIC LOOKUP
       ========================= */

    Optional<OfficialEvidence> findByTypeAndSourceAndSubjectRef(
            EvidenceType type,
            EvidenceSource source,
            String subjectRef
    );

    boolean existsByTypeAndSourceAndSubjectRef(
            EvidenceType type,
            EvidenceSource source,
            String subjectRef
    );

    /* =========================
       FIND ALL (FILTERED)
       ========================= */

    @Query("""
        select e from OfficialEvidence e
        where (:type is null or e.type = :type)
          and (:source is null or e.source = :source)
    """)
    Page<OfficialEvidence> findAll(
            EvidenceType type,
            EvidenceSource source,
            Pageable pageable
    );

    /* =========================
       SEARCH
       ========================= */

    @Query("""
        select e from OfficialEvidence e
        where (:query is null
               or lower(e.subjectRef) like lower(concat('%', :query, '%')))
          and (:type is null or e.type = :type)
          and (:source is null or e.source = :source)
    """)
    Page<OfficialEvidence> search(
            String query,
            EvidenceType type,
            EvidenceSource source,
            Pageable pageable
    );
}
