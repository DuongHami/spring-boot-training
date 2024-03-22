package com.example.springboottraining.service;

import com.example.springboottraining.entity.ToDo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoService {

    ToDo getToDoById(Long id);
    Boolean createToDo(ToDo obj);
    ToDo updateToDo(ToDo obj);
    void deleteToDo(ToDo obj);
    void deleteToDoById(Long id);
    List<ToDo> getAllToDo();
    List<ToDo> getFinishedToDo();
    List<ToDo> getOpenToDo();
    Long getNumOfOpenToDo();
    Long getNumOfFinishedToDo();
}
