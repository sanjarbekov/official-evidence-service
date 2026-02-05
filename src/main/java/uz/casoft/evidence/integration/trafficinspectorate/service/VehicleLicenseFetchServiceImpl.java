package uz.casoft.evidence.integration.trafficinspectorate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.casoft.evidence.dto.*;
import uz.casoft.evidence.integration.trafficinspectorate.client.VehicleLicenseClient;
import uz.casoft.evidence.integration.trafficinspectorate.dto.VehicleLicenseRequestDto;
import uz.casoft.evidence.integration.trafficinspectorate.dto.VehicleLicenseResponseDto;
import uz.casoft.evidence.model.enums.EvidenceSource;
import uz.casoft.evidence.model.enums.EvidenceType;
import uz.casoft.evidence.service.OfficialEvidenceService;
import uz.casoft.evidence.service.OfficialEvidenceSnapshotService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleLicenseFetchServiceImpl
        implements VehicleLicenseFetchService {

    private final VehicleLicenseClient client;
    private final OfficialEvidenceService evidenceService;
    private final OfficialEvidenceSnapshotService snapshotService;
    private final ObjectMapper objectMapper;

    @Override
    public OfficialEvidenceSnapshotDto fetchAndStore(
            VehicleLicenseRequestDto request
    ) {
        // 1. Call State Traffic Inspectorate
        String rawJson = client.fetchRaw(request);

        // 2. Minimal validation
        VehicleLicenseResponseDto parsed = parse(rawJson);
        if (parsed.getResultCode() == null || parsed.getResultCode() != 1) {
            throw new IllegalStateException(
                    "Traffic Inspectorate response error: "
                            + parsed.getResultMessage()
            );
        }

        // 3. Evidence container
        var evidence = evidenceService.getOrCreate(
                OfficialEvidenceCreateDto.builder()
                        .type(EvidenceType.VEHICLE)
                        .source(EvidenceSource.STATE_TRAFFIC_INSPECTORATE)
                        .subjectRef(normalizePlate(request.getPlateNumber()))
                        .build()
        );

        // 4. Immutable snapshot
        return snapshotService.create(
                OfficialEvidenceSnapshotCreateDto.builder()
                        .evidenceId(evidence.getId())
                        .rawPayload(rawJson)
                        .requestId(request.getRequestId())
                        .validAt(LocalDate.now())
                        .build()
        );
    }

    private VehicleLicenseResponseDto parse(String rawJson) {
        try {
            return objectMapper.readValue(rawJson, VehicleLicenseResponseDto.class);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to parse Traffic Inspectorate response", e
            );
        }
    }

    private String normalizePlate(String plate) {
        return plate == null ? null : plate.trim().toUpperCase();
    }
}
