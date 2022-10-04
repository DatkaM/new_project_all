package peaksoft.new_project_all.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import peaksoft.new_project_all.security.jwt.JwtTokenVerifier;

@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenVerifier jwtTokenVerifier;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests(
                        (auth)->auth.antMatchers("/swagger",
                                "/v3/api-docs/**",
                                "/swagger-ui/index.html").permitAll()
                                .anyRequest().permitAll()
                );
        http.addFilterBefore(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
