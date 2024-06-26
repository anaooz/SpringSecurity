package io.github.anaooz.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SenhaMasterAuthenticationProvider senhaMasterAuthenticationProvider,
            CustomAuthenticationProvider customAuthenticationProvider,
            CustomFilter customFilter
            ) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers("/public").permitAll();
                    customizer.anyRequest().authenticated(); //sempre por último, caso esqueça de validar alguma rota
                })
                .httpBasic(Customizer.withDefaults()) //precisa de senha
                .formLogin(Customizer.withDefaults()) //se conecta com o formulário
                .authenticationProvider(senhaMasterAuthenticationProvider) //pode botar quantos providers quiser, subscreve o userdetails abaixo
                .authenticationProvider(customAuthenticationProvider)
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class) //qual filto, antes de qual outro filto
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails commonUser = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(commonUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); //vazio se não quiser prefixo, pode colocar ROLE_, GRUPO_, etc.
    }
}
