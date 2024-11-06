package com.mysite.sbimgshop2.codegroups;

import com.mysite.sbimgshop2.common.dto.PageDTO;

public interface CodeGroupService {
	
	 void createCodeGroup(CodeGroupDTO createCodeGroupDTO);
		//  List<CodeGroupDTO> findBy
		PageDTO getCodeGroups(int page, int size);
		
		PageDTO getCodeGroups(String codeGroup, String codeName, int page, int size);
		
		CodeGroupDTO getCodeGroup(String groupCode);
}
