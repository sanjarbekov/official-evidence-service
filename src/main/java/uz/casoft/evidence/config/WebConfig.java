package uz.casoft.evidence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = "app.web", name = "cors-enabled", havingValue = "true", matchIfMissing = false)
public class WebConfig implements WebMvcConfigurer {

    @Value("#{'${app.web.cors-origins:}'.split(',')}")
    private List<String> corsOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = corsOrigins.stream()
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);

        registry.addMapping("/**")
                .allowedOrigins(origins.length == 0 ? new String[]{"http://localhost:5173"} : origins)
                .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}
