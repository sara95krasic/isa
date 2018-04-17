package rs.ac.uns.ftn.informatika.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	                .antMatchers("/", "/bootstrap/**", "/images/**", "/js/**", "/theater-basic.html", "/registration.html", "/public/**", "/test/**").permitAll()
	                .antMatchers("/theater-management.html", "/theater_management/**").hasAuthority("ADMIN_THEATER")
	                .anyRequest().fullyAuthenticated()
                .and()
	                .formLogin()
	                .loginPage("/login.html")
	                .failureUrl("/login.html")
	                .defaultSuccessUrl("/", true)
	                .usernameParameter("email")
	                .passwordParameter("passwordHash")
	                .permitAll()
                .and()
	                .logout()
	                .logoutUrl("/logout")
	                .deleteCookies("remember-me")
	                .logoutSuccessUrl("/")
	                .permitAll()
                .and()
                	.rememberMe()
            	.and()
            		.csrf().disable().exceptionHandling()
                .and()
                	.headers().xssProtection().disable()
                .and()
                	.sessionManagement()
                	.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                	.maximumSessions(1);
        
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
