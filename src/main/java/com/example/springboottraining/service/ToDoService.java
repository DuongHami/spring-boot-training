package com.example.springboottraining.service;

import com.example.springboottraining.entity.ToDo;

import java.util.List;

public interface ToDoService {

    void createToDo(ToDo obj);
    void updateToDo(ToDo obj);
    void deleteToDo(ToDo obj);
    List<ToDo> getAllToDo();
    List<ToDo> getFinishedToDo();
    List<ToDo> getOpenToDo();
    Long getNumOfOpenToDo();
    Long getNumOfFinishedToDo();
}
