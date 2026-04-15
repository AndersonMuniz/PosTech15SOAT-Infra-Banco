package br.com.fiap.numberone.shared.security;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.numberone.shared.security.JwtAuthenticationFilter.AuthenticatedAdmin;

@RestController
@RequestMapping("/api/admin/session")
public class AdminSessionController {

	@GetMapping
	public SessionResponse currentSession(Authentication authentication) {
		AuthenticatedAdmin principal = (AuthenticatedAdmin) authentication.getPrincipal();
		return new SessionResponse(principal.username(), principal.role(), true);
	}

	public record SessionResponse(String username, String role, boolean authenticated) {
	}
}
