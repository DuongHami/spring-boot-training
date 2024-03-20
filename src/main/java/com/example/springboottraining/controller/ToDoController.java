package com.example.springboottraining.controller;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.service.ToDoServiceImpl;
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
    @PostMapping(consumes = "application/json")
    public void postToDO(@RequestBody ToDo newToDo){
        this.getToDoServiceImpl().createToDo(newToDo);
    }

    /**
     * Update a ToDo
     */
    @PutMapping
    public void putToDo(@RequestBody ToDo newToDo){
        this.getToDoServiceImpl().updateToDo(newToDo);
    }

    /**
     * Delete a ToDo
     */
    @DeleteMapping
    public void deleteToDo(@RequestBody ToDo newToDo){
        this.getToDoServiceImpl().deleteToDo(newToDo);
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
