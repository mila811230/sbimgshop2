package com.mysite.sbimgshop2.codegroups;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping
	public ResponseEntity<CodeGroupDTO> register(@RequestBody CodeGroupDTO codeGroupDTO) {
		log.info("register : {}", codeGroupDTO);
		
		codeGroupService.register(codeGroupDTO);
		return ResponseEntity.ok(codeGroupDTO);
		
	}
	
}
