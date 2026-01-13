package miguel.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. DESHABILITAR CORS AQUÍ (El API Gateway es el único jefe de esto)
                .cors(cors -> cors.disable())

                // 2. Deshabilitar CSRF
                .csrf(csrf -> csrf.disable())

                // 3. Reglas de URL
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/productos/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }

}