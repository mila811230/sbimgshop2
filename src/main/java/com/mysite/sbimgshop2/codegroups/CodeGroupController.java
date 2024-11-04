package com.mysite.sbimgshop2.codegroups;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/codegroups")
public class CodeGroupController {

	private final CodeGroupService codeGroupService;
	
}
