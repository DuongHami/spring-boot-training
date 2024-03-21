package com.example.springboottraining.service;

import com.example.springboottraining.entity.ToDo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoService {

    ToDo getToDoById(Long id);
    void createToDo(ToDo obj);
    void updateToDo(ToDo obj);
    void deleteToDo(ToDo obj);
    void deleteToDoById(Long id);
    List<ToDo> getAllToDo();
    List<ToDo> getFinishedToDo();
    List<ToDo> getOpenToDo();
    Long getNumOfOpenToDo();
    Long getNumOfFinishedToDo();
}
