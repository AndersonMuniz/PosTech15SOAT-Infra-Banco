package br.com.fiap.numberone.shared.security;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, UUID> {

	Optional<AdminUser> findByUsernameIgnoreCase(String username);
}
