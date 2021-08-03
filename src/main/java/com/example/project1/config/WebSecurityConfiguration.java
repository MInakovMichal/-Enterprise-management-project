package com.example.project1.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private final SecurityDetailsProvider securityDetailsProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(securityDetailsProvider);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/").authenticated().and()
                .authorizeRequests().antMatchers("/registration").anonymous().and()
                .authorizeRequests().antMatchers("/worker/workerCalendar/**/editWorkerCalendarDetails").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER").and()
                .authorizeRequests().antMatchers("/worker/workerCalendar/**/workerCalendarDetails").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER").and()
                .authorizeRequests().antMatchers("/worker/addWorker").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER").and()
                .authorizeRequests().antMatchers("/worker/update/**").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER").and()
                .authorizeRequests().antMatchers("/worker/delete/**").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER").and()
                .authorizeRequests().antMatchers("/workPlace/addWorkPlace").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER").and()
                .authorizeRequests().antMatchers("/workPlace/updateWorkPlace/**").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER").and()
                .authorizeRequests().antMatchers("/workPlace/deleteWorkPlace/**").hasAnyRole("ENGINEER", "CONSTRUCTION_MANAGER", "DIRECTOR", "HOLDER");

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/worker")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/worker")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}