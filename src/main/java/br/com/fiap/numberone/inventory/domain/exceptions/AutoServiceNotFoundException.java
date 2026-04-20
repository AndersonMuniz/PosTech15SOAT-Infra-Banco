package br.com.fiap.numberone.inventory.domain.exceptions;

public class AutoServiceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AutoServiceNotFoundException(String message) {
        super(message);
    }
}
