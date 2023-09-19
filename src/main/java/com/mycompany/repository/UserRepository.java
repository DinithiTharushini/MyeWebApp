package com.mycompany.repository;

import com.mycompany.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findByFirstnameContainingIgnoreCase(String keyword);
}
