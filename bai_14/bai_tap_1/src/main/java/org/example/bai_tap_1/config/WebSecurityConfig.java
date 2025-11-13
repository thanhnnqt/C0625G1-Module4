package org.example.bai_tap_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorize -> authorize
                // Public URLs
                .requestMatchers("/", "/blogs", "/login", "/logout", "/logoutSuccessful", "/403").permitAll()

                // USER role: POST (add) và PATCH (update)
                .requestMatchers(HttpMethod.POST, "/blogs").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/blogs/**").hasRole("USER")

                // USER hoặc ADMIN: DELETE blog
                .requestMatchers(HttpMethod.DELETE, "/blogs/**").hasAnyRole("USER", "ADMIN")

                // ADMIN: /admin
                .requestMatchers("/admin").hasRole("ADMIN")

                // Bắt buộc authentication cho tất cả URL còn lại
                .anyRequest().authenticated()
        );

        // Form login
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/userInfo", true)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
        );

        // Logout
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        );

        // Khi không đủ quyền
        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));

        return http.build();
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails testUser = User.withUsername("user1")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin1")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(testUser, admin);
    }
}
