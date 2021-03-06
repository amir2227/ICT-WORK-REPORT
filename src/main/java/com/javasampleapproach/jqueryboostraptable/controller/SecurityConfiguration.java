package com.javasampleapproach.jqueryboostraptable.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	
	
	    @Qualifier("userDetailsServiceImpl")
	    @Autowired
	    private UserDetailsService userDetailsService;
	

	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.
	            authorizeRequests()
	            .antMatchers("/findAllUser").permitAll()
	            .antMatchers("/saveUser","/app/**").permitAll()
	            .antMatchers("/login*").permitAll()
	            .antMatchers("/registration").permitAll()
	            .antMatchers("/members","/report","/states").hasAuthority("ADMIN").anyRequest()
	            .authenticated().and().csrf().disable().formLogin()
	            .loginPage("/login").failureUrl("/login?error=true")
	            .defaultSuccessUrl("/profile")
	            .usernameParameter("personalId")
	            .passwordParameter("pass")
	            .and().logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login").and().exceptionHandling()
	            .accessDeniedPage("/access-denied");
	 
	}
	 @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	            .antMatchers("/resources/**", "/error","/static/**", "/css/**", "/js/**", "/images/**", "/h2-console/**");
	}

	
}
