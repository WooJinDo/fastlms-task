package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {

    private long id;
    private String bannerName; //배너명
    private String link;    //링크주소
    private String target;    //target open 방법 - 새창인지, 현재창

    private boolean usingYn; //공개여부
    private int sortValue; // 정렬순서

    private LocalDateTime regDt;// 동록일(추가날짜)
    private LocalDateTime udtDt;// 수정일(수정날짜)

    private String filename;
    private String urlFilename;

    //추가컬럼
    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
         return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .link(banner.getLink())
                .target(banner.getTarget())
                .usingYn(banner.isUsingYn())
                .sortValue(banner.getSortValue())
                .regDt(banner.getRegDt())
                .udtDt(banner.getUdtDt())
                .filename(banner.getFilename())
                .urlFilename((banner.getUrlFilename()))
                .build();
    }

    public static List<BannerDto> of(List<Banner> banners) {
        if (banners == null) {
            return null;
        }

        List<BannerDto> bannerList = new ArrayList<>();
        for (Banner b : banners) {
            bannerList.add(BannerDto.of(b));
        }
        return bannerList;

    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
