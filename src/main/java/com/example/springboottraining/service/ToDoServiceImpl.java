package com.example.springboottraining.service;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.repository.ToDoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    @Override
    public void createToDo(ToDo obj) {
        this.getToDoRepository().save(obj);
    }

    @Override
    public void updateToDo(ToDo obj) {
        ToDo newObj = this.getToDoRepository().findById(obj.getId()).get();
        newObj.setTaskname(obj.getTaskname());
        newObj.setDescription(obj.getDescription());
        newObj.setCompleated(obj.getCompleated());
        this.getToDoRepository().save(obj);
    }

    @Override
    public void deleteToDo(ToDo obj) {
        this.getToDoRepository().delete(obj);
    }

    @Override
    public List<ToDo> getAllToDo() {
        return (List<ToDo>) this.getToDoRepository().findAll();
    }

    @Override
    public List<ToDo> getFinishedToDo() {
        return (List<ToDo>) this.getToDoRepository().findByCompleated(true);
    }

    @Override
    public List<ToDo> getOpenToDo() {
        return (List<ToDo>) this.getToDoRepository().findByCompleated(false);
    }

    @Override
    public Integer getNumOfOpenToDo() {
        return (Integer)((List<ToDo>) this.getToDoRepository().findByCompleated(false)).size();
    }

    @Override
    public Integer getNumOfFinishedToDo() {
        return (Integer)((List<ToDo>) this.getToDoRepository().findByCompleated(true)).size();
    }
}
