package com.mysite.sbimgshop2.codegroups;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeGroupDTO {

	private String groupCode;
	
	private String groupName;
	
	private String useYn;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;


// Request -> DTO 변환
public static CodeGroupDTO from(CreateCodeGroupRequest request) {
	CodeGroupDTO dto = new CodeGroupDTO();
	dto.setGroupCode(request.getGroupCode());
	dto.setGroupName(request.getGroupName());
	dto.setUseYn(request.getUseYn());
	
	return dto;
	
 }
}
