package com.zerobase.fastlms.banner.model;

import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Data;

@Data
public class BannerParam extends CommonParam {

    private long id;// banner.id

    private String target;

}
