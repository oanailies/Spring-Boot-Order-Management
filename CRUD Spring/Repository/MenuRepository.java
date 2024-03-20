package com.example.CRUD_Tutorial.Repository;


import com.example.CRUD_Tutorial.Entity.Menu;
import com.example.CRUD_Tutorial.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

    Optional<Menu> findById(Integer id);

}

