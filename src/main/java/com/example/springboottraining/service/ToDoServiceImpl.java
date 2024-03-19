package com.example.springboottraining.service;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.repository.ToDoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
        if(this.getToDoRepository().existsById(obj.getId())){
            ToDo newToDo = this.getToDoRepository().findById(obj.getId()).get();
            newToDo.setCompleted(obj.getCompleted());
            newToDo.setTaskname(obj.getTaskname());
            newToDo.setDescription(obj.getDescription());
            newToDo.setId(obj.getId());
            this.getToDoRepository().save(newToDo);
        }
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
        return this.getToDoRepository().findByCompleted(true);
    }

    @Override
    public List<ToDo> getOpenToDo() {
        return this.getToDoRepository().findByCompleted(false);
    }

    @Override
    public Long getNumOfOpenToDo() {
        return this.getToDoRepository().countAllByCompletedIsFalse();
    }

    @Override
    public Long getNumOfFinishedToDo() {
        return this.getToDoRepository().countAllByCompletedIsTrue();
    }
}
