package com.study.exception.domain.group.exception;

import com.study.exception.global.error.ErrorCode;
import com.study.exception.global.exception.ApiException;

public class GroupException extends ApiException {

	public GroupException(ErrorCode errorCode) {
		super(errorCode);
	}
}
