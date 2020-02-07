package com.frobom.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Bean("userAuthenticationProvider")
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Autowired
    @Qualifier("userAuthenticationProvider")
    private DaoAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().antMatchers("/user/login", "/", "/home","/signup", "/account/confirm", "/account/forget-password", "/account/reset-password").permitAll()
            .antMatchers("/user/**").access("hasRole('USER')")
            .anyRequest().authenticated()
            .and().formLogin().loginProcessingUrl("/user/login")
            .loginPage("/user/login").usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/hello")
            .and().logout().logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID")
            .and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");*/
        http.antMatcher("/user*")
        .authorizeRequests()
        .anyRequest()
        .hasRole("USER")
         
        .and()
        .formLogin().usernameParameter("email").passwordParameter("password")
        .loginPage("/user/login")
        .loginProcessingUrl("/user-login")
        .defaultSuccessUrl("/user")
        .and()
        .logout()
        .logoutUrl("/user_logout")
        .logoutSuccessUrl("/protectedLinks")
        .deleteCookies("JSESSIONID")
        .and()
        .exceptionHandling()
        .accessDeniedPage("/Access_Denied")
        .and()
        .csrf().disable();
     }
}
