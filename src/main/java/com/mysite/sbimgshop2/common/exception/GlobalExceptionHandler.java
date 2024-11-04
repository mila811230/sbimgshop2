package com.mysite.sbimgshop2.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	// 비즈니스 예외처리
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> HandleBusinessException(BusinessException e) {
		log.error("BusinessException", e);
		
		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
		
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
