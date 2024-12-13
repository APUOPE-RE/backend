package com.apuope.apuope_re.filters;

import com.apuope.apuope_re.services.JWTService;
import com.apuope.apuope_re.services.ApuopeUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final ApuopeUserDetailService apuopeUserDetailService;

    public JWTFilter(JWTService jwtService, ApuopeUserDetailService apuopeUserDetailService) {
        this.jwtService = jwtService;
        this.apuopeUserDetailService = apuopeUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String reqHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
        if(reqHeader == null || !reqHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = reqHeader.substring(7);
        email = jwtService.extractEmail(token);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.apuopeUserDetailService.loadUserByUsername(email);
            if(jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
