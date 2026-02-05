package uz.casoft.evidence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.casoft.evidence.dto.OfficialEvidenceSnapshotDto;
import uz.casoft.evidence.model.OfficialEvidenceSnapshot;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OfficialEvidenceSnapshotMapper {

    @Mapping(source = "evidence.id", target = "evidenceId")
    OfficialEvidenceSnapshotDto toDto(OfficialEvidenceSnapshot entity);
}
