package com.mysite.sbimgshop2.codegroups;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeGroupServiceImpl implements CodeGroupService {

	private final CodeGroupMapper codeGroupMapper;
	
	@Override
	public void register(CodeGroupDTO codeGroupDTO) {
		// TODO DuplicateKeyException
		codeGroupMapper.create(codeGroupDTO);
	}
}
