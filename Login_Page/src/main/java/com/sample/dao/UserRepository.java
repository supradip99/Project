package com.sample.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	

}
