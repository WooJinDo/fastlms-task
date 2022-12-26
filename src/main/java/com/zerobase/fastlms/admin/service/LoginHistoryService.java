package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.model.AdminMemberParam;

import java.util.List;

public interface LoginHistoryService {

    /**
     * 회원 로그인 접속 목록 표시
     */
    List<LoginHistoryDto> historyList(AdminMemberParam parameter);
}
