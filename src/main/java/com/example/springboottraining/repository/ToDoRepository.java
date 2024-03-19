package com.example.springboottraining.repository;

import com.example.springboottraining.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends CrudRepository <ToDo, Long> {

}
