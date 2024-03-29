package packages.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter
{
	@Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    private String tokenHeader = "token";
    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  throws IOException, ServletException 	
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader(this.tokenHeader);
        
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        String username = tokenUtils.getUsernameFromToken(token);

        if (username != null &&
            SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (tokenUtils.validateToken(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

}
