package com.mysite.sbimgshop2.codegroups;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.sbimgshop2.common.dto.PageDTO;
import com.mysite.sbimgshop2.common.exception.CustomValidationException;
import com.mysite.sbimgshop2.common.exception.ErrorCode;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/codegroups")
public class CodeGroupController {

	private final CodeGroupService codeGroupService;
	
	@PostMapping
	public ResponseEntity<CodeGroupDTO> register(@Valid @RequestBody CodeGroupDTO codeGroupDTO,
												BindingResult bindingResult) {
		log.info("register : {}", codeGroupDTO);
		
		if(bindingResult.hasErrors()) {
			throw new CustomValidationException(ErrorCode.VALIDATION_ERROR, bindingResult);
		}
		
		codeGroupService.register(codeGroupDTO);
		
		return ResponseEntity.ok(codeGroupDTO);
	}
	
	@GetMapping
	public ResponseEntity<PageDTO<CodeGroupDTO>> getCodeGroups(@RequestParam(name="page", defaultValue = "1") int page,
			@RequestParam(name="size", defaultValue = "10") int size, Model model) {
		PageDTO<CodeGroupDTO> pageDTO = codeGroupService.getCodeGroups(page, size);
		
		return ResponseEntity.ok(pageDTO);
	}
	
}
