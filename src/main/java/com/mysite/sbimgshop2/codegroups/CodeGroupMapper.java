package com.mysite.sbimgshop2.codegroups;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CodeGroupMapper {
	
	// pk가 group_code 임에 주의
	@Insert("INSERT INTO code_group (group_code, group_name) VALUES (#{groupCode}, #{groupName})")
		void insert(CodeGroupDTO codeGroupDTO);

	
	@Select("SELECT group_code, group_name, use_yn, is_deleted, created_at, updated_at " +
            "FROM  code_group")
		List<CodeGroupDTO> selectAll();
	
//	@Select("SELECT group_code, group_name, use_yn, is_deleted, created_at, updated_at " 
//			+ "FROM code_group ORDER BY group_code DESC LIMIT #{size} OFFSET #{offset}")
		List<CodeGroupDTO> selectByCondition(@Param("condition") CodeGroupSearchCondition condition,
				@Param("offset") int offset, @Param("size") int size);
	
	@Select("SELECT * FROM code_group WHERE group_code = #{groupCode}")
	Optional<CodeGroupDTO> selectByGroupCode(@Param("groupCode") String groupCode);
	
	@Select("SELECT EXISTS(SELECT 1 FROM code_group WHERE group_code = #{groupCode})")
		boolean exists(@Param("groupCode") String groupCode);
	
	@Delete("DELETE FROM code_group WHERE group_code = #{groupCode}")
		void delete(@Param("groupCode") String groupCode);
	
	@Select("SELECT count(*) FROM code_group")
		int countTotal();
				
}
