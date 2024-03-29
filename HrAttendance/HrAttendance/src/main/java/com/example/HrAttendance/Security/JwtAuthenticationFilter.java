package com.example.HrAttendance.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get token
        String requestToken = request.getHeader("Authorization");
        System.out.println(requestToken);
        String userName=null;
        String token=null;
        if(requestToken!=null && requestToken.startsWith("Bearer ")){
            token=requestToken.substring(7);
            try{
                userName=this.jwtTokenHelper.getUserNameFromToken(token);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                System.out.println("unable to get Jwt token!");
            }catch (ExpiredJwtException e){
                e.printStackTrace();
                System.out.println("Jwt token has expired!");
            }catch (MalformedJwtException e){
                e.printStackTrace();

                System.out.println("invalid Jwt!!");
            }
        }else {
            System.out.println("Jwt token does not begin with Bearer!");
        }
        // once we get the token validate

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if(this.jwtTokenHelper.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }else{
                System.out.println("invalid jwt token!");
            }

        }else{
            System.out.println("username is null or context is not null");
        }
        filterChain.doFilter(request,response);
    }

}
