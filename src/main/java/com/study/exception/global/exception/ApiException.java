package com.study.exception.global.exception;

import com.study.exception.global.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {

	private final ErrorCode errorCode;
}
