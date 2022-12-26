package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.AdminMemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(AdminMemberParam parameter);
    List<MemberDto> selectList(AdminMemberParam parameter);

}
