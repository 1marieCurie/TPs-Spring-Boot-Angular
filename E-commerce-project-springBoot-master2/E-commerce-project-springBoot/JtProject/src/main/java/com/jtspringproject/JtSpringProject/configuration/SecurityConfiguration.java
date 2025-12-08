package com.jtspringproject.JtSpringProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jtspringproject.JtSpringProject.model.User;
import com.jtspringproject.JtSpringProject.services.userService;

@Configuration
public class SecurityConfiguration {

    private final userService userService;

    public SecurityConfiguration(userService userService) {
        this.userService = userService;
    }

    // ===================== Admin Security =====================
    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter {

        @Bean
        SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/admin/**")
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers(new AntPathRequestMatcher("/admin/login")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                    )
                    .formLogin(login -> login
                            .loginPage("/admin/login")
                            .loginProcessingUrl("/admin/loginvalidate")
                            .successHandler((request, response, authentication) ->
                                    response.sendRedirect("/admin/"))
                            .failureHandler((request, response, exception) ->
                                    response.sendRedirect("/admin/login?error=true"))
                    )
                    .logout(logout -> logout
                            .logoutUrl("/admin/logout")
                            .logoutSuccessUrl("/admin/login")
                            .deleteCookies("JSESSIONID"))
                    .exceptionHandling(exception ->
                            exception.accessDeniedPage("/403"))
                    .csrf(csrf -> csrf.disable());

            return http.build();
        }
    }

    // ===================== User Security =====================
    @Configuration
    @Order(2)
    public static class UserConfigurationAdapter {

        @Bean
        SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(requests -> requests
                            .antMatchers("/login", "/register", "/newuserregister", "/test", "/test2").permitAll()
                            .antMatchers("/**").hasAnyRole("USER", "ADMIN")

                    )
                    .formLogin(login -> login
                            .loginPage("/login")
                            .loginProcessingUrl("/userloginvalidate")
                            .successHandler((request, response, authentication) ->
                                    response.sendRedirect("/"))
                            .failureHandler((request, response, exception) ->
                                    response.sendRedirect("/login?error=true"))
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .deleteCookies("JSESSIONID"))
                    .exceptionHandling(exception ->
                            exception.accessDeniedPage("/403"))
                    .csrf(csrf -> csrf.disable());

            return http.build();
        }
    }

    // ===================== UserDetailsService & PasswordEncoder =====================
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            String role = user.getRole().replace("ROLE_", ""); // normalise le rôle
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword()) // mot de passe déjà encodé
                    .roles(role)
                    .build();
        };
    }

}
