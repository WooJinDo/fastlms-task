package com.zerobase.fastlms.banner.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
