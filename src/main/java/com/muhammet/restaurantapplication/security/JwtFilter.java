package com.muhammet.restaurantapplication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhammet.restaurantapplication.model.responses.ClaimsResponse;
import com.muhammet.restaurantapplication.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token=header.substring(7);
        ClaimsResponse validateResult = tokenService.validateToken(token);
        String username = validateResult.getSub();

        if (username == null){
            filterChain.doFilter(request, response);
            return;
        }

        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        User userDetails = new User(validateResult.getSub(),"", grantedAuthorities);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }
    private void sendError(HttpServletResponse res, Exception e) throws IOException {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        Map<String, String> errors = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        errors.put("error", e.getMessage());
        out.println(mapper.writeValueAsString(errors));
        out.flush();
    }

    private String getToken(HttpServletRequest request){
        String header =request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header==null || !header.startsWith("Bearer ")){
            return "";
        }
        return  header.substring(7);
    }
}
