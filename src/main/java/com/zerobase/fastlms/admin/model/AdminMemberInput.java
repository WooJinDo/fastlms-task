package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class AdminMemberInput {
    String userId;
    String userStatus;
    String password;
}
