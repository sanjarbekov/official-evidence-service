package uz.casoft.evidence.integration.trafficinspectorate.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import uz.casoft.evidence.integration.trafficinspectorate.config.TrafficInspectorateProperties;
import uz.casoft.evidence.integration.trafficinspectorate.dto.VehicleLicenseRequestDto;

@Component
@RequiredArgsConstructor
public class VehicleLicenseClientImpl implements VehicleLicenseClient {

    @Qualifier("trafficInspectorateWebClient")
    private final WebClient webClient;

    private final TrafficInspectorateProperties properties;

    @Override
    public String fetchRaw(VehicleLicenseRequestDto request) {
        return webClient
                .post()
                .uri(properties.getUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
