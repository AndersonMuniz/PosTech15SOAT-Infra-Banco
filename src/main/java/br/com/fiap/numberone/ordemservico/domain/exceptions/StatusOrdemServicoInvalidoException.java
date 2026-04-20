package br.com.fiap.numberone.ordemservico.domain.exceptions;

public class StatusOrdemServicoInvalidoException extends RuntimeException {
    public StatusOrdemServicoInvalidoException(String message) {
        super(message);
    }
}