package com.example.demo.openid_connect.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.example.demo.openid_connect.model.OpenIdConnUserDetails;
import com.example.demo.openid_connect.properties.CoogleConnProperties;

/**
 * OpenId Connect authentication Filter
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */
public class OpenIdConnectAuthFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	OAuth2RestTemplate restTemplate;

	@Autowired
	CoogleConnProperties connProps;

	public OpenIdConnectAuthFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		
		// Set dummy one because null is prohibited.
		setAuthenticationManager(authentication -> authentication);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		final ResponseEntity<OpenIdConnUserDetails> entity = restTemplate.getForEntity(connProps.getUserInfoUri(),
				OpenIdConnUserDetails.class);
		return new PreAuthenticatedAuthenticationToken(entity.getBody(), null, AuthorityUtils.NO_AUTHORITIES);
	}

}
