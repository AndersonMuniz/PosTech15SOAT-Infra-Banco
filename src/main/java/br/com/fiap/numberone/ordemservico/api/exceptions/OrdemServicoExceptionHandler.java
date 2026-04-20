package br.com.fiap.numberone.ordemservico.api.exceptions;

import br.com.fiap.numberone.ordemservico.domain.exceptions.StatusOrdemServicoInvalidoException;
import br.com.fiap.numberone.shared.api.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackages = "br.com.fiap.numberone.ordemservico")
public class OrdemServicoExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(OrdemServicoExceptionHandler.class);

    @ExceptionHandler(StatusOrdemServicoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(StatusOrdemServicoInvalidoException ex) {
        log.warn("Status da ordem de serviço não permite a ação executada: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.unprocessableContent().body(response);
    }


}
