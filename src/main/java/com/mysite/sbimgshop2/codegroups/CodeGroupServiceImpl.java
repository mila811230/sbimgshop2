package com.mysite.sbimgshop2.codegroups;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public void createCodeGroup(CreateCodeGroupRequest createCodeGroupRequest) {
		// TODO DuplicateKeyException
		try {
			codeGroupMapper.insert(createCodeGroupRequest);
			
		} catch (DuplicateKeyException e){
			throw new BusinessException(ErrorCode.DUPLICATE_KEY, e);
		}
	}

	@Override
	public PageDTO<CodeGroupResponse> getCodeGroups(int page, int size) {
		
		int offset = (page - 1) * size;
		int totalElements = codeGroupMapper.countTotal();
		int totalPages = (int) Math.ceil((double) totalElements / size);
		
		CodeGroupSearchCondition condition = CodeGroupSearchCondition.builder()
				// .groupCode("001")
				.build();
		
		List<CodeGroupDTO> codeGroupDTOS = codeGroupMapper.selectByCondition(condition, offset, size);
		
		// DTO 를 Response로 변환
		List<CodeGroupResponse> response = codeGroupDTOS.stream()
				.map(CodeGroupResponse::from)
				.collect(Collectors.toList());
				
		return PageDTO.<CodeGroupResponse>builder()
				.page(page)
				.size(size)
				.totalPages(totalPages)
				.totalElements(totalElements)
				.content(response)
				.build();
	}
	
	@Override
	public PageDTO<CodeGroupResponse> getCodeGroups(String codeGroup, String codeName, String useYn, int page, int size) {
		
		int offset = (page - 1) * size;
		int totalElements = codeGroupMapper.countTotal();
		int totalPages = (int) Math.ceil((double) totalElements / size);
		
		CodeGroupSearchCondition condition = CodeGroupSearchCondition.builder()
				.groupCode(codeGroup)
				.groupName(codeName)
				.useYn(useYn)
				.build();
		
		List<CodeGroupDTO> codeGroupDTOS = codeGroupMapper.selectByCondition(condition, offset, size);
		
		// DTO 를 Response로 변환
		List<CodeGroupResponse> codeGroupResponses = codeGroupDTOS.stream()
				.map(CodeGroupResponse::from)
				.collect(Collectors.toList());
				
		return PageDTO.<CodeGroupResponse>builder()
				.page(page)
				.size(size)
				.totalPages(totalPages)
				.totalElements(totalElements)
				.content(codeGroupResponses)
				.build();
	}
	
	@Override
	public CodeGroupDTO getCodeGroup(String groupCode) {
		
		return codeGroupMapper.selectByGroupCode(groupCode).orElseThrow(() -> new BusinessException(ErrorCode.NO_DATA_FOUND));
		}
	
	@Override
	public CodeGroupDTO updateCodeGroup(String groupCode, UpdateCodeGroupRequest updateCodeGroupRequest) {
		
		// TODO 소유자 또는 관리자 권한 체크
		
		if(!codeGroupMapper.exists(groupCode)) {
			throw new BusinessException(ErrorCode.NO_DATA_FOUND);
		}
		
		codeGroupMapper.updateCodeGroup(groupCode, updateCodeGroupRequest);
		
		return codeGroupMapper.selectByGroupCode(groupCode).orElseThrow();
	}
	
	@Override
	public CodeGroupDTO partialUpdateCodeGroup(String groupCode, Map<String, Object> updates) {
		
		// TODO 소유자 또는 관리자 권한 체크
		
		if(!codeGroupMapper.exists(groupCode)) {
			throw new BusinessException(ErrorCode.NO_DATA_FOUND);
		
		}
		
		codeGroupMapper.partialUpdateCodeGroup(groupCode, updates);
		
		return codeGroupMapper.selectByGroupCode(groupCode).orElseThrow();
	}
	
	@Override
	public void deleteCodeGroup(CodeGroupDTO codeGroupDTO) {
		codeGroupMapper.delete(codeGroupDTO.getGroupCode());
	}
}
