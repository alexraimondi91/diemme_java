package com.diemme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diemme.business.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
		    .passwordEncoder(bCryptPasswordEncoder)
		    ;
	}

	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()

			        .antMatchers("/home","/","/login","/registration","/backoffice/**").permitAll()
			        .antMatchers("/dashboard/**").authenticated()
			        .antMatchers("/tecnologieCrea").hasAuthority("ADMIN") 
			        //.antMatchers("/prodotti/", "/ssds/").hasAnyRole("admin")
					//.antMatchers("/preventivi/", "/ssds/").hasAnyRole("client")
			        .and()
			        .csrf().disable().formLogin()
			        .loginPage("/login").failureUrl("/login?error=true")
			        .defaultSuccessUrl("/dashboard",true)
			        .usernameParameter("user_name")
			        .passwordParameter("password")
			        .and()
		            .logout()
		            .logoutUrl("/logout")
		            .logoutSuccessUrl("/login")
		            .and()
		            .exceptionHandling()
			        .accessDeniedPage("/access-denied");
	}

}
