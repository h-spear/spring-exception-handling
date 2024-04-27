package com.study.exception.global.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.study.exception.global.error.ErrorCode;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private final T data;
	private final long timestamp;
	private final boolean success;

	private ApiResponse(T data, boolean success) {
		this.data = data;
		this.timestamp = System.currentTimeMillis();
		this.success = success;
	}

	public static <T> ResponseEntity<ApiResponse<T>> success(T data, HttpStatus httpStatus) {
		return new ResponseEntity<>(new ApiResponse<>(data, true), httpStatus);
	}

	public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
		return new ResponseEntity<>(new ApiResponse<>(data, true), HttpStatus.OK);
	}

	public static ResponseEntity<ApiResponse<?>> error(ErrorCode errorCode) {
		return new ResponseEntity<>(new ApiResponse<>(getErrorData(errorCode), false), errorCode.getHttpStatus());
	}

	public static ResponseEntity<ApiResponse<?>> fail(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		for (ObjectError error: allErrors) {
			if (error instanceof FieldError) {
				errors.put(((FieldError) error).getField(), error.getDefaultMessage());
			} else {
				errors.put( error.getObjectName(), error.getDefaultMessage());
			}
		}
		return new ResponseEntity<>(new ApiResponse<>(errors, false), HttpStatus.BAD_REQUEST);
	}

	private static Map<String, String> getErrorData(ErrorCode errorCode) {
		Map<String, String> map = new HashMap<>();
		map.put("code", errorCode.name());
		map.put("message", errorCode.getMessage());
		return map;
	}
}
