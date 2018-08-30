package com.local.security.dao;

import com.local.security.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RolesEntity,Long> {

}
