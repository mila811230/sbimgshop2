package com.mysite.sbimgshop2.common.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class CustomValidationException extends BusinessException {

	private final BindingResult bindingResult;
	
	public CustomValidationException(ErrorCode errorCode, BindingResult bindingResult) {
		super(errorCode);
		this.bindingResult = bindingResult;
	}
}
