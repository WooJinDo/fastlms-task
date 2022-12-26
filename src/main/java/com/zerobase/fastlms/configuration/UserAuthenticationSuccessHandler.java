package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.admin.entity.LoginHistory;
import com.zerobase.fastlms.admin.repository.LoginHistoryRepository;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

//<!--실습1-->
@Slf4j
@RequiredArgsConstructor
@Component
public class UserAuthenticationSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginHistoryRepository loginHistoryRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        LoginHistory loginHistory = LoginHistory.builder()
                .userId(authentication.getName())
                .ip(RequestUtils.getIp(request))
                .userAgent(RequestUtils.getUserAgent(request))
                .lastLoginTime(LocalDateTime.now())
                .build();
        loginHistoryRepository.save(loginHistory);

        log.info("로그인에 성공하였습니다");
        log.info("아이디: " + authentication.getName());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
