package uz.casoft.evidence;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.casoft.evidence.integration.trafficinspectorate.config.TrafficInspectorateProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties(TrafficInspectorateProperties.class)
public class OfficialEvidenceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(OfficialEvidenceApplication.class, args);
    }
}