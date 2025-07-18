package com.xzit.rental.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFalseHandler loginFalseHandler;

    @Autowired
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Autowired
    private CustomerAnonymousEntryPoint customerAnonymousEntryPoint;

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private VerifyTokenFilter verifyTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customerUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
        http.authenticationProvider(daoAuthenticationProvider);
            //登录前进行过滤
            http.addFilterBefore(verifyTokenFilter, UsernamePasswordAuthenticationFilter.class);
            //登录过程处理
        http.formLogin()
                .loginProcessingUrl("/rental/user/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFalseHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/rental/user/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customerAccessDeniedHandler)
                .authenticationEntryPoint(customerAnonymousEntryPoint)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .userDetailsService(customerUserDetailService);
        return http.build();
    }
}
