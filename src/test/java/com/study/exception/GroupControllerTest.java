package com.study.exception;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.study.exception.domain.group.controller.GroupController;
import com.study.exception.domain.group.error.GroupErrorCode;
import com.study.exception.domain.group.exception.GroupException;
import com.study.exception.domain.group.service.GroupService;
import com.study.exception.global.error.ErrorCode;

@WebMvcTest(controllers = GroupController.class)
class GroupControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GroupService groupService;

	private final ErrorCode[] errorCodes = {null,
		GroupErrorCode.GROUP_NOT_FOUND,
		GroupErrorCode.USER_NOT_IN_GROUP,
		GroupErrorCode.INVALID_GROUP_USER_ROLE,
		GroupErrorCode.INVALID_GROUP_OR_USER,
		GroupErrorCode.USER_ALREADY_IN_GROUP,
		GroupErrorCode.NO_GROUP_MANAGEMENT_PERMISSION,
		GroupErrorCode.GROUP_MANAGER_CANNOT_LEAVE,
		GroupErrorCode.DOES_NOT_MATCH_NUMBER_OF_GROUPS
	};

	@Test
	void apiTestFail1() throws Exception {
		apiCallExceptionTest(1);
	}

	@Test
	void apiTestFail2() throws Exception {
		apiCallExceptionTest(2);
	}

	@Test
	void apiTestFail3() throws Exception {
		apiCallExceptionTest(3);
	}

	@Test
	void apiTestFail4() throws Exception {
		apiCallExceptionTest(4);
	}

	@Test
	void apiTestFail5() throws Exception {
		apiCallExceptionTest(5);
	}

	@Test
	void apiTestFail6() throws Exception {
		apiCallExceptionTest(6);
	}

	@Test
	void apiTestFail7() throws Exception {
		apiCallExceptionTest(7);
	}

	@Test
	void apiTestFail8() throws Exception {
		apiCallExceptionTest(8);
	}

	@Test
	void apiTestOk() throws Exception {
		final boolean success = true;
		final ResultMatcher status = status().isOk();

		given(groupService.call(99))
			.willReturn(true);

		ResultActions resultActions = mvc.perform(get("/api?input=" + 99)
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding(StandardCharsets.UTF_8));

		resultActions
			.andDo(print())
			.andExpect(status)
			.andExpect(jsonPath("$.timestamp").exists())
			.andExpect(jsonPath("$.success").value(success))
			.andExpect(jsonPath("$.data").doesNotExist());
	}

	private void apiCallExceptionTest(int input) throws Exception {
		final boolean success = false;
		final String message = errorCodes[input].getMessage();
		final ResultMatcher status = status().is(errorCodes[input].getHttpStatus().value());

		given(groupService.call(input))
			.willThrow(new GroupException(errorCodes[input]));

		ResultActions resultActions = mvc.perform(get("/api?input=" + input)
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding(StandardCharsets.UTF_8));

		resultActions
			.andDo(print())
			.andExpect(status)
			.andExpect(jsonPath("$.timestamp").exists())
			.andExpect(jsonPath("$.success").value(success))
			.andExpect(jsonPath("$.data.message").value(message));
	}
}