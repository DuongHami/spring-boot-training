package com.example.springboottraining.config;

import com.example.springboottraining.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SimpleAliasRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .cors(Customizer,)
//                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authentication) -> authentication
                        .requestMatchers("/", "index.html").permitAll()
                        .requestMatchers("helloWorld.html").hasRole("HELLO_WORLD")
                        .anyRequest().authenticated()
                ).httpBasic(withDefaults());;
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        // USER
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .authorities(Role.USER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(Role.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password(passwordEncoder.encode("analyst"))
                .authorities(Role.ANALYST.getGrantedAuthorities())
                .build();

//        UserDetails helloWorld = User.builder()
//                .username("hw")
//                .password(passwordEncoder.encode("hw"))
//                .roles("HELLO_WORLD")
//                .build();

        return new InMemoryUserDetailsManager(user,admin, analyst);
    }
}
