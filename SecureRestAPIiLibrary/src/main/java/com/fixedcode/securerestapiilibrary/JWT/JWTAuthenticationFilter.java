package com.fixedcode.securerestapiilibrary.JWT;

import com.fixedcode.securerestapiilibrary.Security.LibraryUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final LibraryUserDetailsService libraryUserDetailsService;
//    When user logins and authenticated then token is generated.
//    Token is created by setting subject of username with expiration time and
//    signed authority.
//    When authorisation request goes to server then bearer containing token is also sent to server through request.
//    Token is then validated by extracting claims to get username and expiry datetime.
//    Extracted details of token are compare with DB user details.
//    If token is valid then, we set user details to token.
//    We add filter before user details authorisation is done to create stateless session
//    as we are not saving anything in cookies.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Authorization is key and <brearer token> is value
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            userName = jwtService.extractUsernameFromToken(token);
        }
        if (userName != null & SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = libraryUserDetailsService.loadUserByUsername(userName);
            if(jwtService.validateToken(token, userDetails)) {
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}