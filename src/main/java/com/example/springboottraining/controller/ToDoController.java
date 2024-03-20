package com.example.springboottraining.controller;

import com.example.springboottraining.dto.ToDoCreateDTO;
import com.example.springboottraining.dto.ToDoDeleteDTO;
import com.example.springboottraining.dto.ToDoUpdateDTO;
import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.service.ToDoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/todo")
public class ToDoController {

    private final ToDoServiceImpl toDoServiceImpl;

    /**
     * Create a ToDo
     * @param newToDo the ToDo object to be created
     */
    @PostMapping
    public void postToDO(@Valid @RequestBody ToDoCreateDTO newToDo){
        this.getToDoServiceImpl().createToDo(new ToDo(null, newToDo.getTaskname(), newToDo.getDescription(), false));
    }

    /**
     * Update a ToDo
     */
    @PutMapping
    public void putToDo(@Valid @RequestBody ToDoUpdateDTO newToDo){
        this.getToDoServiceImpl().updateToDo(
                new ToDo(newToDo.getId(), newToDo.getTaskname(), newToDo.getDescription(), newToDo.getCompleted())
        );
    }

    /**
     * Delete a ToDo
     */
    @DeleteMapping
    public void deleteToDo(@Valid @RequestBody ToDoDeleteDTO newToDo){
        this.getToDoServiceImpl().deleteToDo(new ToDo(newToDo.getId(), newToDo.getTaskname(), newToDo.getDescription(), newToDo.getCompleted()));
    }

    @DeleteMapping("/{id}")
    public void deleteToDoById(@PathVariable Long id){
        this.getToDoServiceImpl().deleteToDoById(id);
    }

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable Long id){
        return this.getToDoServiceImpl().getToDoById(id);
    }

    /**
     * Read all ToDo objects
     * @return
     */
    @GetMapping(value = "/all")
    public List<ToDo> getAllTodo(){
        return this.getToDoServiceImpl().getAllToDo();
    }


    @GetMapping(path = "/finished")
    public List<ToDo> getAllFinishedTodo(){
        return this.getToDoServiceImpl().getFinishedToDo();
    }

    @GetMapping(path = "/open")
    public List<ToDo> getAllOpenTodo(){
        return this.getToDoServiceImpl().getOpenToDo();
    }

    @GetMapping(path = "/numfinished")
    public Long getNumOfFinishedTodo(){
        return this.getToDoServiceImpl().getNumOfFinishedToDo();
    }

    @GetMapping(path = "/numopen")
    public Long getNumOfOpenTodo(){
        return this.getToDoServiceImpl().getNumOfOpenToDo();
    }
//
//    @GetMapping("/{num}")
//    public String helloWithNum(@PathVariable("num") Integer num){
//        return "Hello " + num;
//    }

//    @DeleteMapping("/{id}")
//    public void deleteToDo(@PathVariable("id") Long id){
//        getToDoServiceImpl().deleteToDo(id);
//    }
}
