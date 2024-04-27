package com.study.exception.domain.group.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.exception.domain.group.service.GroupService;
import com.study.exception.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GroupController {

	private final GroupService groupService;

	@GetMapping
	public ResponseEntity<?> api(@RequestParam(name = "input", defaultValue = "0") int input) {
		log.info("api call");
		groupService.call(input);
		return ApiResponse.success(null);
	}
}
