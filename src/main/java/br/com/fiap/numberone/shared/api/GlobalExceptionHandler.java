package br.com.fiap.numberone.shared.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import br.com.fiap.numberone.shared.security.InvalidCredentialsException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException exception, HttpServletRequest request) {
		Map<String, String> fieldErrors = new LinkedHashMap<>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return ResponseEntity.badRequest()
			.body(ApiErrorResponse.of(HttpStatus.BAD_REQUEST, "Dados da requisicao sao invalidos.", request.getRequestURI(), fieldErrors));
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidCredentials(InvalidCredentialsException exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(ApiErrorResponse.of(HttpStatus.UNAUTHORIZED, exception.getMessage(), request.getRequestURI()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException exception, HttpServletRequest request) {
		return ResponseEntity.badRequest()
			.body(ApiErrorResponse.of(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI()));
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFound(NoResourceFoundException exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(ApiErrorResponse.of(HttpStatus.NOT_FOUND, "Recurso nao encontrado.", request.getRequestURI()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleUnexpected(Exception exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ApiErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado.", request.getRequestURI()));
	}
}
