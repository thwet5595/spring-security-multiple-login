package com.frobom.hr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AdminLoginFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) arg0;
        request.getSession().setAttribute("companyName", request.getParameter("companyName"));
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }

}
