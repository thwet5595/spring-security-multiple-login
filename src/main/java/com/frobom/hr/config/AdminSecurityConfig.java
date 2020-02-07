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

import com.frobom.hr.service.impl.AdminUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Autowired
    @Qualifier("adminUserDetailsService")
    private AdminUserDetailsServiceImpl adminUserDetailsService;

    @Bean("adminAuthenticationProvider")
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(adminUserDetailsService);
        return authenticationProvider;
    }
    
    @Autowired
    @Qualifier("adminAuthenticationProvider")
    private DaoAuthenticationProvider adminAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(adminAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().antMatchers("/admin/login").permitAll()
                .antMatchers("/admin").access("hasRole('ADMIN')")
                //.anyRequest().authenticated()
                .and().formLogin().loginPage("/admin-login").usernameParameter("username")
                .passwordParameter("password").defaultSuccessUrl("/hello-admin").loginProcessingUrl("/admin/login")
                .and().logout().logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID").and().csrf().and()
                .exceptionHandling().accessDeniedPage("/Access_Denied");*/
        http.antMatcher("/admin*")
        .authorizeRequests()
        .anyRequest()
        .hasRole("ADMIN")
        .and()
        .formLogin().usernameParameter("username").passwordParameter("password")
        .loginPage("/admin/login")
        .loginProcessingUrl("/admin-login")
        .defaultSuccessUrl("/admin")
        .and()
        .logout()
        .logoutUrl("/admin_logout")
        .logoutSuccessUrl("/protectedLinks")
        .deleteCookies("JSESSIONID") 
        .and()
        .exceptionHandling()
        .accessDeniedPage("/Access_Denied")
        .and()
        .csrf().disable();
    }
}
