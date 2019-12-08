package com.nitesh.infodev.demo.newsblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nitesh.infodev.demo.newsblog.utility.SecurityUtility;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	private static final String[] PUBLIC_MATCHERS = { "/common/**", "/fonts/**", "/plugin-frameworks/**", "/images/**",
			"/", "/login/**", "/user/add/**" };

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder1());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated().and().formLogin()
				.permitAll().loginPage("/login").defaultSuccessUrl("/").loginProcessingUrl("/dologin").and().logout()
				.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/dologout", "POST")).logoutSuccessUrl("/");
	}

	@SuppressWarnings("unused")
	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private PasswordEncoder passwordEncoder1() {
		return NoOpPasswordEncoder.getInstance();
	}
}
