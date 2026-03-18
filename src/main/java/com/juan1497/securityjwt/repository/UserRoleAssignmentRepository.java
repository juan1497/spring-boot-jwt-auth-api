package com.juan1497.securityjwt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juan1497.securityjwt.model.UserRoleAssignment;

public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, UUID> {

}