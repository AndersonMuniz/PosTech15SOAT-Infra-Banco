package br.com.fiap.numberone.shared.security;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminAuthenticationService {

	private final AdminUserRepository adminUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final SecurityProperties securityProperties;

	public AdminAuthenticationService(
		AdminUserRepository adminUserRepository,
		PasswordEncoder passwordEncoder,
		JwtService jwtService,
		SecurityProperties securityProperties
	) {
		this.adminUserRepository = adminUserRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.securityProperties = securityProperties;
	}

	@Transactional
	public void bootstrapAdminIfNeeded() {
		if (adminUserRepository.count() > 0) {
			return;
		}

		SecurityProperties.BootstrapAdmin bootstrapAdmin = securityProperties.getBootstrapAdmin();
		AdminUser adminUser = new AdminUser(
			UUID.randomUUID(),
			bootstrapAdmin.getUsername(),
			passwordEncoder.encode(bootstrapAdmin.getPassword()),
			bootstrapAdmin.getRole(),
			true
		);

		adminUserRepository.save(adminUser);
	}

	@Transactional(readOnly = true)
	public AuthResponse authenticate(AuthRequest request) {
		AdminUser adminUser = adminUserRepository.findByUsernameIgnoreCase(request.username())
			.filter(AdminUser::isEnabled)
			.orElseThrow(InvalidCredentialsException::new);

		if (!passwordEncoder.matches(request.password(), adminUser.getPasswordHash())) {
			throw new InvalidCredentialsException();
		}

		return new AuthResponse(
			jwtService.generateToken(adminUser),
			"Bearer",
			securityProperties.getJwt().getAccessTokenExpiration().toSeconds()
		);
	}

	public record AuthRequest(String username, String password) {
	}

	public record AuthResponse(String accessToken, String tokenType, long expiresInSeconds) {
	}
}
