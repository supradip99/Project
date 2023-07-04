package com.sample.dao;

import org.springframework.data.repository.CrudRepository;

import com.sample.entity.User;

public interface Repository extends CrudRepository<User, Integer> {

}
