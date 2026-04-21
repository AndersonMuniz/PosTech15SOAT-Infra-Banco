package br.com.fiap.numberone.automotiveservice.domain.exceptions;

public class AutomotiveServiceBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AutomotiveServiceBusinessException(String message) {
        super(message);
    }
}
