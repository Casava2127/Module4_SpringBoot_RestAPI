package com.ra.repository;

import com.ra.model.entity.UserRole;
import com.ra.model.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
