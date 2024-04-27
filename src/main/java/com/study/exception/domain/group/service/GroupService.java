package com.study.exception.domain.group.service;

import org.springframework.stereotype.Service;

import com.study.exception.domain.group.error.GroupErrorCode;
import com.study.exception.domain.group.exception.GroupException;
import com.study.exception.global.error.CommonErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupService {

	public void call(int input) {
		log.info("service call");
		switch (input) {
			case 1 -> throw new GroupException(GroupErrorCode.GROUP_NOT_FOUND);
			case 2 -> throw new GroupException(GroupErrorCode.USER_NOT_IN_GROUP);
			case 3 -> throw new GroupException(GroupErrorCode.INVALID_GROUP_USER_ROLE);
			case 4 -> throw new GroupException(GroupErrorCode.INVALID_GROUP_OR_USER);
			case 5 -> throw new GroupException(GroupErrorCode.USER_ALREADY_IN_GROUP);
			case 6 -> throw new GroupException(GroupErrorCode.NO_GROUP_MANAGEMENT_PERMISSION);
			case 7 -> throw new GroupException(GroupErrorCode.GROUP_MANAGER_CANNOT_LEAVE);
			case 8 -> throw new GroupException(GroupErrorCode.DOES_NOT_MATCH_NUMBER_OF_GROUPS);
		}
	}
}
