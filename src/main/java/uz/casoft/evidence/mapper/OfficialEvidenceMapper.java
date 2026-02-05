package uz.casoft.evidence.mapper;

import org.mapstruct.*;
import uz.casoft.evidence.dto.*;
import uz.casoft.evidence.model.OfficialEvidence;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OfficialEvidenceMapper {

    /* =========================
       ENTITY → DTO
       ========================= */
    OfficialEvidenceDto toDto(OfficialEvidence entity);

    /* =========================
       CREATE DTO → ENTITY
       ========================= */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    OfficialEvidence toEntity(OfficialEvidenceCreateDto dto);
}
