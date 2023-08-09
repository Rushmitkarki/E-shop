package com.ecommerce.ecommerce.security;


import com.ecommerce.ecommerce.config.PasswordEncoderUtil;
import com.ecommerce.ecommerce.service.impl.SecurityUserDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final SecurityUserDetailService securityUserDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationConfigurer(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(securityUserDetailService);
        authenticationProvider.setPasswordEncoder(PasswordEncoderUtil.getInstance());
        return authenticationProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login","/register","/CSS/**","/JS/**","/IMG/**","/webjars/**","/save","/verify/**","/forgotPassword/**","/resetPassword/**","seller/verify/**")
                .permitAll()
                .requestMatchers("/seller/**")
                .hasAuthority("Seller")
                .requestMatchers("/buyer/**")
                .hasAuthority("Buyer")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(new RoleBasedAuthenticationSuccessHandler())
                .usernameParameter("email")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return httpSecurity.build();
    }
    private static class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("Seller")) {
                    response.sendRedirect("/seller/dashboard");
                    return;
                }
            }
            response.sendRedirect("/buyer/dashboard");
        }
    }

}
