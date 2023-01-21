package com.project.server.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.RedirectStrategy;

import java.io.IOException;

public class NoRedirectStrategy implements RedirectStrategy {
    @Override
    public void sendRedirect(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String url
    ) throws IOException {
        // No redirect is required with pure REST
    }
}
