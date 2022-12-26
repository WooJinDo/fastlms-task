package com.zerobase.fastlms.banner.controller;

import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BannerController extends BaseController {

    private final BannerService bannerService;

//    @GetMapping("/banner")
//    public String course(Model model, BannerParam parameter) {
//
//        List<BannerDto> list = bannerService.frontList(parameter);
//        model.addAttribute("list", list);
//
//        return "banner/index";
//    }

 }
