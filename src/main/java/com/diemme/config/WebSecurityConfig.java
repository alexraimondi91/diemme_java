package com.diemme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().disable().csrf().disable()
		.formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password").permitAll()
				.failureUrl("/loginFailed").defaultSuccessUrl("/", false)
				.and()
				.logout()
				.logoutSuccessUrl("/")
				.and()
				.exceptionHandling().accessDeniedPage("/login")
				.and()
				.authorizeRequests()
				// Specificare le url che sono soggette ad autenticazione ed autorizzazione
				.antMatchers("/login", "/static/", "/favicon.ico").permitAll()
				.antMatchers("/common/**").authenticated()
				.antMatchers("/prodotti/**", "/ssds/**").hasAnyRole("ADMIN")
				.antMatchers("/preventivi/**", "/ssds/**").hasAnyRole("CLIENT");


	}

}
