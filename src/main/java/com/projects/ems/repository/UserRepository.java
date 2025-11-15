package com.projects.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.ems.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User getByEmail(String email);

}
