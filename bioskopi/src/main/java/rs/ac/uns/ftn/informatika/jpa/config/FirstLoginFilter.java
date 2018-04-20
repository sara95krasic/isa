package rs.ac.uns.ftn.informatika.jpa.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.filter.GenericFilterBean;

import rs.ac.uns.ftn.informatika.jpa.domain.CurrentUser;
import rs.ac.uns.ftn.informatika.jpa.domain.Role;

public class FirstLoginFilter extends GenericFilterBean {
	 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //chain.doFilter(request, response);

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            if( authentication.getPrincipal() instanceof CurrentUser) {
                CurrentUser cu = (CurrentUser) authentication.getPrincipal();
	            	
	            String currentUrl = UrlUtils.buildRequestUrl((HttpServletRequest) request);
	            
	            //(ovo za bootstrap je cisto da mu pusti i boostrap, da changecredentials.html izgleda malo lepse)
	            //(a za bootstrap mu treba jquery)
	            if("/changecredentials.html".equals(currentUrl) ||
	            		currentUrl.startsWith("/bootstrap") ||
	            		currentUrl.startsWith("/js/jquery") ||
	            		currentUrl.startsWith("/registered/change_pass")) {
	                chain.doFilter(request, response);
	                return;
	            } else if ((cu.getRole() == Role.ADMIN_THEATER && !cu.hasLoggedInBefore()) ||
	            		(cu.getRole() == Role.ADMIN_FAN && !cu.hasLoggedInBefore())) {
	                ((HttpServletResponse) response).sendRedirect("/changecredentials.html");
	                return;
		        }
            }
	    }

        chain.doFilter(request, response);
    	
    	/*
    	
    	//Should redirect occur or it shouldn't.
		boolean redirect = false;
		
		
		if (!(request instanceof HttpServletRequest)) {
	        throw new ServletException("Can only process HttpServletRequest");
	    }
	
	    if (!(response instanceof HttpServletResponse)) {
	        throw new ServletException("Can only process HttpServletResponse");
	    }
	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication != null) {
		    if( authentication.getPrincipal() instanceof CurrentUser) {
		    	CurrentUser cu = (CurrentUser) authentication.getPrincipal();
		    
		    	if(!cu.hasLoggedInBefore()) 
		    		redirect = true;
		    } 
	    }

	    String a = ((HttpServletRequest)request).getServletPath();
	    String b = ((HttpServletRequest)request).getRequestURI();
	    HttpServletRequest hrq = (HttpServletRequest)request;
	    String c = hrq.toString();
	    
	    if(a.equals("/changecredentials.html") || b.equals("/changecredentials.html")) 
	    	redirect = false;
	           
	   	if(redirect)
	   	{
	   		ServletContext context = ((HttpServletRequest)request).getSession().getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/changecredentials.html");
			if(rd != null) {
				rd.forward(request, response);
			}
	   	}
	   	else
	   		chain.doFilter(request, response);
    	
    	*/
    }
}