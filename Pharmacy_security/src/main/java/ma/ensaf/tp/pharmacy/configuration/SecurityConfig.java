package ma.ensaf.tp.pharmacy.configuration;

import ma.ensaf.tp.pharmacy.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // // brancher le service et l'encoder pour que Spring Security l’utilise
    public SecurityConfig(CustomUserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {  
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    
    // Configure Spring Security pour utiliser le service et l’encodeur.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        // Ainsi, Spring sait : 
        // Où chercher les utilisateurs (dans la base via ton service),
        // Comment comparer les mots de passe (en utilisant BCrypt)
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
            	.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                .requestMatchers("/", "/login", "/logout").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/medicaments").hasAnyRole("ADMIN", "PHARMACIEN", "CLIENT", "CAISSIER")
                .requestMatchers("/medicaments/new", "/medicaments/*/edit", "/medicaments/*/delete").hasAnyRole("ADMIN", "PHARMACIEN")
                .requestMatchers("/categories", "/fournisseurs").hasAnyRole("ADMIN", "PHARMACIEN")
                .requestMatchers("/categories/new", "/categories/*/edit", "/fournisseurs/new", "/fournisseurs/*/edit").hasRole("ADMIN")
                .requestMatchers("/ventes", "/ventes/new", "/ventes/**").hasAnyRole("CAISSIER", "PHARMACIEN", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied")
            );
        return http.build();
        // authorizeHttpRequests avec requestMatchers : contrôle d’accès selon les rôle
        // formLogin + logout : configure les pages de login/logout. 
        // exceptionHandling : page personnalisée pour accès refusé.
    }
}