package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//<!--실습1-->
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginHistoryDto {

    private long id;

    private String userId;

    private String ip;
    private String userAgent;

    private LocalDateTime lastLoginTime; // 마지막 로그인 일자

    // 추가 컬럼
    long totalCount;
    long seq;

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .userId(loginHistory.getUserId())
                .ip(loginHistory.getIp())
                .userAgent(loginHistory.getUserAgent())
                .lastLoginTime(loginHistory.getLastLoginTime())

                .build();
    }

    public String getLastLoginTimeText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return lastLoginTime != null ? lastLoginTime.format(formatter) : "";
    }

}
