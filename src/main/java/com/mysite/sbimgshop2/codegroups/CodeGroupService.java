package com.mysite.sbimgshop2.codegroups;

import com.mysite.sbimgshop2.common.dto.PageDTO;

public interface CodeGroupService {
	
	 void createCodeGroup(CreateCodeGroupRequest createCodeGroupRequest);
	 
		//  List<CodeGroupDTO> findBy
	 
		PageDTO<CodeGroupResponse> getCodeGroups(int page, int size);
		
		PageDTO <CodeGroupResponse> getCodeGroups(String codeGroup, String codeName, int page, int size);
		
		CodeGroupDTO getCodeGroup(String groupCode);
}
