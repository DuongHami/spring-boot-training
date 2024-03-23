package com.example.springboottraining.service;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.exception.ToDoNotFoundException;
import com.example.springboottraining.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    @Override
    public Boolean createToDo(ToDo obj) {
        try{
            ToDo newtodo = this.getToDoRepository().save(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ToDo updateToDo(ToDo obj) {
        if(this.getToDoRepository().existsById(obj.getId())){
            ToDo newToDo = this.getToDoRepository().findById(obj.getId()).get();
            newToDo.setCompleted(obj.getCompleted());
            newToDo.setTaskname(obj.getTaskname());
            newToDo.setDescription(obj.getDescription());
            newToDo.setId(obj.getId());
            this.getToDoRepository().save(newToDo);
        }
        return obj;
    }

    @Override
    public void deleteToDo(ToDo obj) {
        this.getToDoRepository().delete(obj);
    }

    @Override
    public void deleteToDoById(Long id){
        this.getToDoRepository().deleteById(id);
    }

    @Override
    public ToDo getToDoById(Long id){
//        if(this.getToDoRepository().existsById(id)){
//            return this.getToDoRepository().findById(id).orElse(null);
//        }
//        return null;
//        return this.getToDoRepository().findById(id).orElseThrow(
//                () -> new EntityNotFoundException(String.format("ToDo with the id %d could not be found", id))
//        );

        return this.getToDoRepository().findById(id).orElseThrow(
                () -> new EntityNotFoundException("")
        );
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
