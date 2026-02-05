package uz.casoft.evidence.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.casoft.evidence.dto.OfficialEvidenceSnapshotCreateDto;
import uz.casoft.evidence.dto.OfficialEvidenceSnapshotDto;
import uz.casoft.evidence.mapper.OfficialEvidenceSnapshotMapper;
import uz.casoft.evidence.model.OfficialEvidence;
import uz.casoft.evidence.model.OfficialEvidenceSnapshot;
import uz.casoft.evidence.repository.OfficialEvidenceRepository;
import uz.casoft.evidence.repository.OfficialEvidenceSnapshotRepository;
import uz.casoft.evidence.service.OfficialEvidenceSnapshotService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class OfficialEvidenceSnapshotServiceImpl
        implements OfficialEvidenceSnapshotService {

    private final OfficialEvidenceRepository evidenceRepository;
    private final OfficialEvidenceSnapshotRepository snapshotRepository;
    private final OfficialEvidenceSnapshotMapper mapper;

    /* =========================
       LIST
       ========================= */

    @Override
    @Transactional(readOnly = true)
    public Page<OfficialEvidenceSnapshotDto> findAll(
            Long evidenceId,
            Pageable pageable
    ) {
        return snapshotRepository
                .findAllByEvidenceIdOrderByFetchedAtAsc(evidenceId, pageable)
                .map(mapper::toDto);
    }

    /* =========================
       GET ONE
       ========================= */

    @Override
    @Transactional(readOnly = true)
    public OfficialEvidenceSnapshotDto findById(Long id) {
        OfficialEvidenceSnapshot entity =
                snapshotRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "OfficialEvidenceSnapshot not found: " + id
                                )
                        );
        return mapper.toDto(entity);
    }

    /* =========================
       LATEST
       ========================= */

    @Override
    @Transactional(readOnly = true)
    public OfficialEvidenceSnapshotDto findLatest(Long evidenceId) {
        OfficialEvidenceSnapshot snapshot =
                snapshotRepository
                        .findTopByEvidenceIdOrderByFetchedAtDesc(evidenceId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "No snapshots found for evidenceId=" + evidenceId
                                )
                        );

        return mapper.toDto(snapshot);
    }

    /* =========================
       CREATE (APPEND-ONLY)
       ========================= */

    @Override
    public OfficialEvidenceSnapshotDto create(
            OfficialEvidenceSnapshotCreateDto dto
    ) {
        OfficialEvidence evidence =
                evidenceRepository.findById(dto.getEvidenceId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "OfficialEvidence not found: " + dto.getEvidenceId()
                                )
                        );

        String checksum = sha256(dto.getRawPayload());

        OfficialEvidenceSnapshot snapshot =
                new OfficialEvidenceSnapshot(
                        evidence,
                        Instant.now(),
                        dto.getValidAt(),
                        dto.getRequestId(),
                        dto.getRawPayload(),
                        checksum
                );

        return mapper.toDto(snapshotRepository.save(snapshot));
    }

    /* =========================
       UTIL
       ========================= */

    private String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(
                    digest.digest(value.getBytes(StandardCharsets.UTF_8))
            );
        } catch (Exception e) {
            throw new IllegalStateException("Checksum calculation failed", e);
        }
    }
}
