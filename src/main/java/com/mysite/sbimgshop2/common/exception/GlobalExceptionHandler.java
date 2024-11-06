package com.mysite.sbimgshop2.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// 메소드 내에서 발생하지 않는 예외들의 처리
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(NoResourceFoundException e) {
		log.error("Method Argsument Type Mismatch Exception", e);
		
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	protected ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
		
		log.error("No static resource ...", e);
		
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	// 비즈니스 예외처리
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> HandleBusinessException(BusinessException e) {
		log.error("BusinessException", e);
		
		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomValidationException.class)
	protected ResponseEntity<ErrorResponse> handleValidationException(CustomValidationException e) {
		log.error("Validation Exception : ", e);
		
		BindingResult bindingResult = e.getBindingResult();
		Map<String, String> errors = ErrorResponse.bindingResultToMap(bindingResult);
		
		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), errors);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	// 다른 예외들도 함께 처리
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> HandleException(Exception e) {
		log.error("Unexpected error occured :", e);
		
		ErrorResponse response = ErrorResponse.builder()
				.code("S001")
				.message("서버 오류가 발생했습니다.")
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(LocalDateTime.now())
				.build();
		
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
