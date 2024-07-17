package com.mandiri.Security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mandiri.Security.exception.AuthenticationException;
import com.mandiri.service.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

            String clientToken = null;
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                clientToken = headerAuth.substring(7);
            }

            if (clientToken != null && jwtUtil.verifyJwtToken(clientToken)) {
                Map<String, String> userInfo = jwtUtil.getUserInfoByToken(clientToken);
                UserDetails user = userService.loadUserById(userInfo.get("userId"));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                request.setAttribute("userId", userInfo.get("userId"));
                System.out.println("Set userId in request: " + userInfo.get("userId")); // Tambahkan log ini
            }
        } catch (JWTVerificationException e) {
            throw new AuthenticationException("Auth Error: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
