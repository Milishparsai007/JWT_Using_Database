package com.jwt.example.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization"); //isme header aajaega token ka.
        //Bearer 2352345235sdfrsfgsdfsdf
        logger.info(" Header :  {}", requestHeader); //ye log krdega jo header mila hai humko.
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            //to ab bearer ke baad ka store krlo token me
            //0 1 2 3 4 5 6 7
            //b e a r e r   token

            token = requestHeader.substring(7);
            try {

                //jwt helper class ki help se hum username get krlenge.
                username = this.jwtHelper.getUsernameFromToken(token);

            } catch (IllegalArgumentException e) {
                //agar wo fetch nahi krpaya to ye exception hoga.
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                //agar token expired hogya to ye block execute hoga
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }


        } else {
            //agar header me token bheja hi nahi ya phir token Bearer se start nahi ho rha hai to
            logger.info("Invalid Header Value !! ");
        }


        //ab agar username aa chuka hai humare paas and sb kuch sahi hai.
        //to ab hum authenticate krwaenge username ko i.e., login krwaenge.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //agat username null nahi hai mtlb usme kuhc hna kuch aaya hai aur us username par koi
            //authentication pehle se set nahi hai i.e., SecurityContextHolder.getContext().getAuthentication() == null
            //to uspr hum ab authentication set krenge.


            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //validate krega ki token me aaya username aur userdetails me stored username same hai ya nahi and wo token expire hai ya nahi.
            //ye method helper class me hai.
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //The SecurityContextHolder is where Spring Security stores the details of who is authenticated.
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                logger.info("Validation fails !!");
            }


        }

        filterChain.doFilter(request, response);
    }
}
