package br.com.fiap.numberone.cliente.domain.exceptions;

public class DocumentoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DocumentoException(String message) {
        super(message);
    }
}
