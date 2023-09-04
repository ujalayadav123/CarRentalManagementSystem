package com.example.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.User;

@Repository
public interface UserLoginRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}
