package uz.casoft.evidence.integration.trafficinspectorate.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "integration.traffic-inspectorate.vehicle-license")
public class TrafficInspectorateProperties {

    /**
     * State Traffic Inspectorate endpoint URL
     */
    private String url;

    /**
     * Request timeout (ms)
     */
    private int timeoutMs = 30000;
}
