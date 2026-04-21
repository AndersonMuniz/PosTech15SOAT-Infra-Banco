package br.com.fiap.numberone.serviceorder.api.exceptions;

import br.com.fiap.numberone.serviceorder.domain.exceptions.InvalidServiceOrderStatusException;
import br.com.fiap.numberone.shared.api.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackages = "br.com.fiap.numberone.ordemservico")
public class ServiceOrderExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ServiceOrderExceptionHandler.class);

    @ExceptionHandler(InvalidServiceOrderStatusException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(InvalidServiceOrderStatusException ex) {
        log.warn("Service order status does not allow the requested action: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                ex.getMessage(),
                List.of()
        );

        return ResponseEntity.unprocessableContent().body(response);
    }
}
