/**
 * This class extends SimpleUrlAuthenticationFailureHandler to handle authentication failure.
 * It overrides the onAuthenticationFailure method to customize the failure behavior.
 */
package com.b7av3.loginapp.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * Overrides the onAuthenticationFailure method to handle authentication failure.
     * It constructs a default failure URL based on the login type and sets it as the default failure URL.
     *
     * @param request     HttpServletRequest object
     * @param response    HttpServletResponse object
     * @param exception   AuthenticationException object
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet-related error occurs
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // Retrieve the login type from the request parameter
        String loginType = request.getParameter("type");

        // Construct the default failure URL based on the login type
        String defaultFailureUrl = "/login?error&type=" + loginType;

        // Set the default failure URL
        setDefaultFailureUrl(defaultFailureUrl);

        // Call the superclass method to handle the authentication failure
        super.onAuthenticationFailure(request, response, exception);
    }
}
