package com.mysite.sbimgshop2.codegroups;

import com.mysite.sbimgshop2.common.dto.PageDTO;

public interface CodeGroupService {

	void register(CodeGroupDTO codeGroupDTO);
	
	// void createCodeGroup(CreateCodeGroupRequest createCodeGroupRequest);
		//  List<CodeGroupDTO> findBy
		PageDTO getCodeGroups(int page, int size);
}
