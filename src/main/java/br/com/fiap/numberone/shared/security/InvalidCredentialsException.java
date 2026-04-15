package br.com.fiap.numberone.shared.security;

public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException() {
		super("Usuario ou senha invalidos.");
	}
}
