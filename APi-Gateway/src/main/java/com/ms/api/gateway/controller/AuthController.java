package com.ms.api.gateway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient auth2AuthorizedClient,
			@AuthenticationPrincipal OidcUser user, Model model) {

		List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
			return grantedAuthority.getAuthority();
		}).collect(Collectors.toList());
		log.info("user email id {}", user.getEmail());
		return ResponseEntity.ok(AuthResponse.builder().userId(user.getEmail())
				.accessToken(auth2AuthorizedClient.getAccessToken().getTokenValue())
				.refreshToken(auth2AuthorizedClient.getRefreshToken().getTokenValue())
				.expireAt(auth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond())
				.authorities(authorities).build());

	}
}
