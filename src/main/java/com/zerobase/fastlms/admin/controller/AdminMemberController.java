package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.AdminMemberInput;
import com.zerobase.fastlms.admin.model.AdminMemberParam;
import com.zerobase.fastlms.admin.service.LoginHistoryService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//<!--실습1-->
@Controller
@RequiredArgsConstructor
public class AdminMemberController extends BaseController {

    private final MemberService memberService;

    private final LoginHistoryService loginHistoryService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, AdminMemberParam parameter) {

        parameter.init();

        List<MemberDto> members = memberService.list(parameter);

        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();

        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(),
                parameter.getPageIndex(), queryString);

        model.addAttribute("list", members);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("totalCount", totalCount);


        return "admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, AdminMemberParam parameter) {

        parameter.init();

        MemberDto member = memberService.detail(parameter.getUserId());
        model.addAttribute("member", member);

        List<LoginHistoryDto> list = loginHistoryService.historyList(parameter);

        long totalCount = 0;
        if (list != null && list.size() > 0) {
            totalCount = list.get(0).getTotalCount();
        }

        String queryString = "";
        StringBuilder sb = new StringBuilder();
        if (parameter.getUserId() != null && parameter.getUserId().length() > 0) {
            sb.append(String.format("userId=%s", parameter.getUserId()));
            queryString = sb.toString();
        }

        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(),
                parameter.getPageIndex(), queryString);

        model.addAttribute("list", list);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("totalCount", totalCount);


        return "admin/member/detail";
    }

    @PostMapping("/admin/member/status.do")
    public String status(Model model, AdminMemberInput parameter) {

        boolean result = memberService.updateStatus(
                parameter.getUserId(),
                parameter.getUserStatus());

        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }

    @PostMapping("/admin/member/password.do")
    public String password(Model model, AdminMemberInput parameter) {

        boolean result = memberService.updatePassword(
                parameter.getUserId(),
                parameter.getPassword());

        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }
}
