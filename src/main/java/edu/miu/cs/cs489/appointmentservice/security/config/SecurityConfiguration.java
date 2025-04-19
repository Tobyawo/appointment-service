package edu.miu.cs.cs489.appointmentservice.security.config;

import edu.miu.cs.cs489.appointmentservice.enums.PermissionEnum;
import edu.miu.cs.cs489.appointmentservice.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().requestMatchers("/public/**").permitAll().anyRequest()
//                .hasRole("USER").and()
//                // Possibly more configuration ...
//                .formLogin() // enable form based log in
//                // set permitAll for all URLs associated with Form Login
//                .permitAll();
//        return http.build();
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/api/v1/auth/*","/api/roles/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                        .requestMatchers("/api/v1/management/**").hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.MEMBER.name())
                                        .requestMatchers("/api/v1/admin").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers("/api/v1/management/admin-write").hasAuthority(PermissionEnum.ADMIN_WRITE.getPermission())
                                        .requestMatchers("/api/v1/management/for-all").hasAnyAuthority(
                                                PermissionEnum.MEMBER_READ.getPermission(),
                                                PermissionEnum.MEMBER_WRITE.getPermission(),
                                                PermissionEnum.ADMIN_WRITE.getPermission(),
                                                PermissionEnum.ADMIN_READ.getPermission()
                                        )
                                        .anyRequest().authenticated()
//                                        .requestMatchers("/api/v1/auth/authenticate").permitAll()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        ;
        return http.build();
    }
}
