package com.kushal.gateway.ApiGateway.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kushal.gateway.ApiGateway.models.AuthResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LogManager.getLogger(AuthController.class);
	
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			) {
		
		logger.info("user email id: {} ", user.getEmail());
		
//		CREATE AuthResponse OBJECT
		AuthResponse authResponse = new AuthResponse();
		
//		SET EMAIL TO authResponse
		authResponse.setUserId(user.getEmail());
//		SET ACCESS TOKEN TO authResponse
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
//		SET REFRESH TOKEN TO authResponse
		authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
//		SET EXPIRE'S AT TO authResponse
		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
//		SET AUTHORITIES TO authResponse
		List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
			return grantedAuthority.getAuthority();
		}).collect(Collectors.toList());
		
		authResponse.setAuthorities(authorities);

		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}
}
