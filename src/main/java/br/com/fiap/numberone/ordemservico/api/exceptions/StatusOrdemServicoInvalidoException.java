package br.com.fiap.numberone.ordemservico.api.exceptions;

public class StatusOrdemServicoInvalidoException extends RuntimeException {
    public StatusOrdemServicoInvalidoException(String message) {
        super(message);
    }
}