package com.example.CRUD_Tutorial.Repository;

import com.example.CRUD_Tutorial.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{
    public Long countById(Integer id);

    User findByUsername(String username);
}
