package com.zerobase.fastlms.admin.repository;

import com.zerobase.fastlms.admin.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

//<!--실습1-->
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
