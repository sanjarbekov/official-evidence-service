package uz.casoft.evidence.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.casoft.evidence.dto.*;
import uz.casoft.evidence.mapper.OfficialEvidenceMapper;
import uz.casoft.evidence.model.OfficialEvidence;
import uz.casoft.evidence.model.enums.*;
import uz.casoft.evidence.repository.OfficialEvidenceRepository;
import uz.casoft.evidence.service.OfficialEvidenceService;

@Service
@RequiredArgsConstructor
@Transactional
public class OfficialEvidenceServiceImpl implements OfficialEvidenceService {

    private final OfficialEvidenceRepository repository;
    private final OfficialEvidenceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<OfficialEvidenceDto> findAll(
            EvidenceType type,
            EvidenceSource source,
            Pageable pageable
    ) {
        return repository.findAll(type, source, pageable)
                .map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OfficialEvidenceDto> search(
            String query,
            EvidenceType type,
            EvidenceSource source,
            Pageable pageable
    ) {
        if (query == null || query.isBlank()) {
            return findAll(type, source, pageable);
        }

        return repository.search(query.trim(), type, source, pageable)
                .map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public OfficialEvidenceDto findById(Long id) {
        OfficialEvidence entity = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("OfficialEvidence not found: " + id)
                );
        return mapper.toDto(entity);
    }

    @Override
    public OfficialEvidenceDto getOrCreate(OfficialEvidenceCreateDto dto) {
        OfficialEvidence entity =
                repository.findByTypeAndSourceAndSubjectRef(
                                dto.getType(),
                                dto.getSource(),
                                dto.getSubjectRef()
                        )
                        .orElseGet(() ->
                                repository.save(
                                        mapper.toEntity(dto)
                                )
                        );

        return mapper.toDto(entity);
    }
}
