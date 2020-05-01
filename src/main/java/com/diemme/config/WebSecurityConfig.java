package com.diemme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public String passwordEncoder() {

		return "password";

	}

	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().disable().csrf().disable().formLogin().loginPage("/login").loginProcessingUrl("/login")
				.failureUrl("/?error=invalidlogin").defaultSuccessUrl("/", false).and().logout()
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/login").and()
				.authorizeRequests()
				// Specificare le url che sono soggette ad autenticazione ed autorizzazione
				.antMatchers("/login", "/static/", "/favicon.ico").permitAll().antMatchers("/common/").authenticated()
				.antMatchers("/prodotti/", "/ssds/").hasAnyRole("admin")
				.antMatchers("/preventivi/", "/ssds/").hasAnyRole("client");


	}

}
