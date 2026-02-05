package uz.casoft.evidence;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "uz.casoft.evidence")
public class OfficialEvidenceServiceApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(OfficialEvidenceServiceApplication.class, args);
    }
}
