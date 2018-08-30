package com.local.security.dao;

import com.local.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

     UserEntity findByName(String name);
}
