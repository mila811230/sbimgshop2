package com.mysite.sbimgshop2.codegroups;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// TODO NoResourceFoundException: No static resource codegroups.
	
	@PostMapping
	public ResponseEntity<CodeGroupResponse> createCodeGroup(@Valid @RequestBody CreateCodeGroupRequest createCodeGroupRequest,
												BindingResult bindingResult) {
		log.info("register : {}", createCodeGroupRequest);
		
		if(bindingResult.hasErrors()) {
			throw new CustomValidationException(ErrorCode.VALIDATION_ERROR, bindingResult);
		}
		
		codeGroupService.createCodeGroup(createCodeGroupRequest);
		
		CodeGroupDTO codeGroupDTO = codeGroupService.getCodeGroup(createCodeGroupRequest.getGroupCode());
		CodeGroupResponse response = CodeGroupResponse.from(codeGroupDTO);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<PageDTO<CodeGroupResponse>> findCodeGroups(@RequestParam(name="page", defaultValue = "1") int page,
																@RequestParam(name="size", defaultValue = "10") int size, Model model) {
		
		PageDTO<CodeGroupResponse> pageDTO = codeGroupService.getCodeGroups(page, size);
		
		return ResponseEntity.ok(pageDTO);
	}
	
	// 검색
	@GetMapping("/search")
	public ResponseEntity<PageDTO<CodeGroupResponse>> findCodeGroups(
			@RequestParam(value = "groupCode", required = false) String groupCode,
			@RequestParam(value = "groupName", required = false) String groupName,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10")int size) {
		
		PageDTO<CodeGroupResponse> pageDTO = codeGroupService.getCodeGroups(groupCode, groupName, page, size);
		
		return ResponseEntity.ok(pageDTO);
	}
	
	@GetMapping("/{groupCode}")
	public ResponseEntity<CodeGroupResponse> getCodeGroup (@PathVariable("groupCode") String groupCode) {
		
		return ResponseEntity.ok(CodeGroupResponse.from(codeGroupService.getCodeGroup(groupCode)));
	}
	
	// 전체 업데이트 (PUT)
//	@PutMapping("/{groupCode}")
//	public ResponseEntity<CodeGroupDTO> updateCodeGroup(
//			@PathVariable String groupCode,
//			@RequestBody @Valid UpdateCodeGroupRequest request) {
//		
//		CodeGroupDTO updatedGroup = codeGroupService.partialUpdate(groupCode, request);
//		 return ResponseEntity.ok(updatedGroup);
//	}
//	
//	// 부분 업데이트 (PATCH)
//	@PatchMapping("/{groupCode}")
//	public ResponseEntity<CodeGroupDTO> partialUpdateCodeGroup(
//			@PathVariable String groupCode,
//			@RequestBody Map<String, Object> updates) {
//		
//		CodeGroupDTO updatedGroup = codeGroupService.partialUpdate(groupCode, updates);
//		 return ResponseEntity.ok(updatedGroup);
//	}
}
