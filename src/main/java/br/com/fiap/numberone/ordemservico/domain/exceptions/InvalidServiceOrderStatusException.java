package br.com.fiap.numberone.ordemservico.domain.exceptions;

public class InvalidServiceOrderStatusException extends RuntimeException {
    public InvalidServiceOrderStatusException(String message) {
        super(message);
    }
}
