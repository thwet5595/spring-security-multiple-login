package com.frobom.hr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frobom.hr.entity.Admin;
import com.frobom.hr.service.AdminService;

@Service("adminUserDetailsService")
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String companyName = (String) request.getSession().getAttribute("companyName");
        System.out.println("Company Name is " + companyName);
        if (companyName.equals("frobo")) {
           if (!username.equals("froboadmin")) {
               throw new UsernameNotFoundException("Admin user not found");
           }
           return new org.springframework.security.core.userdetails.User("froboadmin", "froboadminpassword", true, true,
                   true, true, getGrantedAuthorities(null));
        }
        
        if (companyName.equals("github")) {
            if (!username.equals("githubadmin")) {
                throw new UsernameNotFoundException("Admin user not found");
            }
            return new org.springframework.security.core.userdetails.User("githubadmin", "githubadminpassword", true, true,
                    true, true, getGrantedAuthorities(null));
        }
        throw new UsernameNotFoundException("Admin user not found");
    }

    private List<GrantedAuthority> getGrantedAuthorities(Admin user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
        return authorities;
    }

}
