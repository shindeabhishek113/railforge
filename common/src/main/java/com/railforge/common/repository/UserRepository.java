package com.railforge.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.railforge.common.model.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);

	User save(User user);

}
