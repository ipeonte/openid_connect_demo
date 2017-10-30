package com.example.demo.openid_connect.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.example.demo.openid_connect.security.filter.OpenIdConnectAuthFilter;

/**
 * Configuration class for Spring Security configuration
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/login");
	}

	@Bean
	public OpenIdConnectAuthFilter openIdConnectAuthenticationFilter() {
		return new OpenIdConnectAuthFilter("/login");
	}

	@Bean
	public OAuth2ClientContextFilter oAuth2ClientContextFilter() {
		return new OAuth2ClientContextFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(oAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
				.addFilterAfter(openIdConnectAuthenticationFilter(), OAuth2ClientContextFilter.class)
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/").permitAll().antMatchers(HttpMethod.GET, "/calc").authenticated();
	}
}
