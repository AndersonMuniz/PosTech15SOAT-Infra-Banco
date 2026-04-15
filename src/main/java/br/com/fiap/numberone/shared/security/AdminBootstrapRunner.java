package br.com.fiap.numberone.shared.security;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminBootstrapRunner implements ApplicationRunner {

	private final AdminAuthenticationService adminAuthenticationService;

	public AdminBootstrapRunner(AdminAuthenticationService adminAuthenticationService) {
		this.adminAuthenticationService = adminAuthenticationService;
	}

	@Override
	public void run(ApplicationArguments args) {
		adminAuthenticationService.bootstrapAdminIfNeeded();
	}
}
