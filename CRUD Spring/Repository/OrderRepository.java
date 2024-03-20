package com.example.CRUD_Tutorial.Repository;

import com.example.CRUD_Tutorial.Entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Integer> {


}
