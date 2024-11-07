package com.mysite.sbimgshop2.codegroups;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroupSearchCondition {

	private String groupCode;
	private String groupName;
	private String useYn;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String searchkeyword;
	private List<String> groupCodes;
	private String sortField;
	private String sortDirection;
	
	// 정렬방향
	public enum SortDirection {
		ASC, DESC
	}
}
