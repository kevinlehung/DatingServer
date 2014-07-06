package com.ds.web.config.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String REMEMBER_ME_KEY = UUID.randomUUID().toString();
	private static final String REMEMBER_ME_PARAMETER = "rememberMe";
	
	@Autowired@Qualifier("dsUserDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired@Qualifier("dsAuthenticationSuccessHandler")
	private AuthenticationSuccessHandler dsAuthenticationSuccessHandler;
	
	@Autowired@Qualifier("rememberMeServices")
	private RememberMeServices rememberMeServices;

	/**
	 * Customize REMEMBER_ME_PARAMETER. 
	 * Issue: Default value is "remember-me". We cannot bind this parameter to form
	 * Resolve: Change remember me parameter to "rememberMe"
	 * @return
	 */
	@Bean RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
		rememberMeServices.setParameter(REMEMBER_ME_PARAMETER);
		return rememberMeServices;
	}
	
	@Bean 
	public AuthenticationManager dsAuthenticationManager() throws Exception {
		return authenticationManager();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
    }

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().requireCsrfProtectionMatcher(new DsRequiresCsrfMatcher());
		
		http.authorizeRequests()
        	.antMatchers("/assets/**", "/sec/sign_up.ds").permitAll()
            .anyRequest().hasAuthority("USER")
		.and()
        	.formLogin()
	            .loginPage("/sec/sign_in.ds")
	            .usernameParameter("email")
	            .passwordParameter("password")
	            .loginProcessingUrl("/sec/security_check.ds")
	            .failureHandler(new DsAuthenticationFailureHandler())
	            .successHandler(dsAuthenticationSuccessHandler)
	            .defaultSuccessUrl("/u/list.ds")
	            .permitAll();
		
		http.logout().logoutUrl("/sec/sign_out.ds").logoutSuccessUrl("/sec/sign_in.ds");
		
		http.rememberMe().key(REMEMBER_ME_KEY).rememberMeServices(rememberMeServices);
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().requireCsrfProtectionMatcher(new DsRequiresCsrfMatcher());
			
			http.antMatcher("/ws/**")
				.authorizeRequests()
				//.antMatchers("/ws/sign_in.ds", "/ws/sign_up.ds").permitAll()
				.antMatchers("/ws/sign_in.ds", "/ws/sign_up.ds", "/ws/retrieve_photo.ds").permitAll()
				.antMatchers("/ws/**").hasAuthority("USER")
				.and()
				.httpBasic();
		}
	}

	public static void main (String[] args) {
		System.out.println("4e604e36-3247-41ef-bfcc-789dd9fcf8c2".hashCode());
	}
}
