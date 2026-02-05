package uz.casoft.evidence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> auth
                        // health
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()

                        // dev tools (optional)
                        .requestMatchers("/h2/**").permitAll()

                        // allow OPTIONS for preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // everything else requires JWT
                        .anyRequest().authenticated()
                )

                // JWT Resource Server (SPA-friendly: returns 401/403, no redirects)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        // Needed for H2 console frames in dev (safe if /h2 disabled in prod)
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }
}
