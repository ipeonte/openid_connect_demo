package com.example.demo.openid_connect.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import com.example.demo.openid_connect.properties.CoogleConnProperties;

/**
 * Configuration class for SpringBoot OAuth2 filter
 * 
 * @author Igor Peonte <igor.144@gmail.com>
 *
 */
@Configuration
@EnableOAuth2Client
public class OpenIdConnectConfig {

	@Autowired
	CoogleConnProperties connProps;

	@Value("${client_id}")
	private String clientId;

	@Value("${client_secret}")
	private String clientSecret;

	@Bean
	public OAuth2ProtectedResourceDetails protectResourceDetails() {
		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setAuthenticationScheme(AuthenticationScheme.form);
		details.setClientAuthenticationScheme(AuthenticationScheme.form);
		details.setClientId(clientId);
		details.setClientSecret(clientSecret);
		details.setUserAuthorizationUri(connProps.getUserAuthorizationUri());
		details.setAccessTokenUri(connProps.getAccessTokenUri());
		details.setScope(Arrays.asList("openid"));
		return details;
	}

	@Bean
	public OAuth2RestTemplate googleOpenIdTemplate(OAuth2ClientContext clientContext) {
		return new OAuth2RestTemplate(protectResourceDetails(), clientContext);
	}
}
