package br.com.fiap.numberone.ordemservico.domain.exceptions;

public class CustomerNotActiveException extends RuntimeException {
    public CustomerNotActiveException(String message) {
        super(message);
    }
}
