package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.model.AdminMemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginHistoryMapper {

    long selectListCount(AdminMemberParam parameter);
    List<LoginHistoryDto> selectList(AdminMemberParam parameter);

}
