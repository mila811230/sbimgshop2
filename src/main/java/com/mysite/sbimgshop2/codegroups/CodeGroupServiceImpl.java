package com.mysite.sbimgshop2.codegroups;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.mysite.sbimgshop2.common.exception.BusinessException;
import com.mysite.sbimgshop2.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeGroupServiceImpl implements CodeGroupService {

	private final CodeGroupMapper codeGroupMapper;
	
	@Override
	public void register(CodeGroupDTO codeGroupDTO) {
		// TODO DuplicateKeyException
		try {
			codeGroupMapper.create(codeGroupDTO);
			
		} catch (DuplicateKeyException e){
			throw new BusinessException(ErrorCode.DUPLICATE_KEY, e);
		}
	}
}
