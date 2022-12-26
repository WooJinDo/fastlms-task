package com.zerobase.fastlms.admin.service.impl;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.mapper.LoginHistoryMapper;
import com.zerobase.fastlms.admin.model.AdminMemberParam;
import com.zerobase.fastlms.admin.repository.LoginHistoryRepository;
import com.zerobase.fastlms.admin.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;
    private final LoginHistoryMapper loginHistoryMapper;

    @Override
    public List<LoginHistoryDto> historyList(AdminMemberParam parameter) {

        long totalCount = loginHistoryMapper.selectListCount(parameter);

        List<LoginHistoryDto> list = loginHistoryMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(LoginHistoryDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

}
