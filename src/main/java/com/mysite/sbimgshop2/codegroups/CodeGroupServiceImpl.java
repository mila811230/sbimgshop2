package com.mysite.sbimgshop2.codegroups;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.mysite.sbimgshop2.common.dto.PageDTO;
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

	@Override
	public PageDTO getCodeGroups(int page, int size) {
		int offset = (page - 1) * size;
		int totalElements = codeGroupMapper.countTotal();
		int totalPages = (int) Math.ceil((double) totalElements / size);
		
		List<CodeGroupDTO> codeGroupDTOS = codeGroupMapper.findByCondition(offset, size);
		
		return PageDTO.<CodeGroupDTO>builder()
				.page(page)
				.size(size)
				.totalPages(totalPages)
				.totalElements(totalElements)
				.content(codeGroupDTOS)
				.build();
	}
}
