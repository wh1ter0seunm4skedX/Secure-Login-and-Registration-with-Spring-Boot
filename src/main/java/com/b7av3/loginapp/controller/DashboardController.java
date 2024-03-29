package com.b7av3.loginapp.controller;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    /**
     * Handles requests to display the dashboard.
     *
     * @param model the Model object to populate data for the view
     * @return the view name for the dashboard page
     */
    @GetMapping
    public String showDashboard(Model model) {
        // Retrieve the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        // Check if the user exists
        if (user != null) {
            // Add user information to the model
            model.addAttribute("user", user);

            // Determine user's roles for display
            String roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));
            model.addAttribute("roles", roles); // Add roles to the model

            return "dashboard"; // Unified dashboard view
        } else {
            // Handle case where user is not found
            System.out.println("User not found: " + username); // Consider using a logger
            return "redirect:/login"; // Redirect to login or an error page
        }
    }
}
