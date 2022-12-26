package com.zerobase.fastlms.banner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannerInput {

    private long id;
    private String bannerName; //배너명
    private String link;    //링크주소
    private String target;    //target open 방법 - 새창인지, 현재창
    private boolean usingYn; //공개여부
    private int sortValue; // 정렬순서
    private LocalDateTime regDt;// 동록일(추가날짜)
    private LocalDateTime udtDt;// 수정일(수정날짜)

    //삭제를 위한
    private String idList;

    //ADD
    private String filename;
    private String urlFilename;

}
