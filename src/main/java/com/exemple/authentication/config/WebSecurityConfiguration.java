package com.exemple.authentication.config;

import com.exemple.authentication.config.security.AuthenticationEntryPointImpl;
import com.exemple.authentication.config.security.GestaoAuthenticationProvider;
import com.exemple.authentication.config.security.PortalAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true, mode = AdviceMode.PROXY, order = Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.csrf().disable();
        http.httpBasic().disable();

        http.authorizeRequests()
                .antMatchers("/api/v1/gestao/**", "/api/v1/portal/**").permitAll()
                .anyRequest()
                .denyAll().and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth,
                          @Qualifier("portalUserDetailsService") UserDetailsService portalUserDetailsService,
                          @Qualifier("gestaoUserDetailsService") UserDetailsService gestaoUserDetailsService) {
        auth.authenticationProvider(new PortalAuthenticationProvider(portalUserDetailsService).passwordEncoder(passwordEncoder()))
                .authenticationProvider(new GestaoAuthenticationProvider(gestaoUserDetailsService).passwordEncoder(passwordEncoder()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

}