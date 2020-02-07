package com.frobom.hr.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frobom.hr.entity.User;

@Controller
public class AuthController {

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    @ResponseBody
    public String accessDeniedPage(Model model) {
        return "accessDenied";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        model.addAttribute(new User());
        if (error != null) {
            model.addAttribute("loginError", "Invalid user name or password.");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out.");
        }
        return "userLogin";
    }
    
    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String adminLoginPage(Model model, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        model.addAttribute(new User());
        if (error != null) {
            model.addAttribute("loginError", "Invalid user name or password.");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out.");
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>adminlogin");
        return "adminLogin";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
