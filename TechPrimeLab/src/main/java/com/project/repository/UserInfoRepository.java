package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.UserInfo;

import java.io.Serializable;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Serializable> {
}
