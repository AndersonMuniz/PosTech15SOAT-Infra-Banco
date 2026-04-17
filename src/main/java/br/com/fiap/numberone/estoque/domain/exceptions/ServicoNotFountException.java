package br.com.fiap.numberone.estoque.domain.exceptions;

public class ServicoNotFountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServicoNotFountException(String message) {
        super(message);
    }
}
