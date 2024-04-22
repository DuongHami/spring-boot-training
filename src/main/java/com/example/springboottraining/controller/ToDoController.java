package com.example.springboottraining.controller;

import com.example.springboottraining.annotation.IsAllowedToCreate;
import com.example.springboottraining.annotation.IsAllowedToDelete;
import com.example.springboottraining.annotation.IsAllowedToRead;
import com.example.springboottraining.annotation.IsAllowedToUpdate;
import com.example.springboottraining.dto.ToDoCreateDTO;
import com.example.springboottraining.dto.ToDoDeleteDTO;
import com.example.springboottraining.dto.ToDoUpdateDTO;
import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.enums.Right;
import com.example.springboottraining.service.ToDoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/todo")
@CrossOrigin
public class ToDoController {

    private final ToDoServiceImpl toDoServiceImpl;

    private final ModelMapper modelMapper;

    /**
     * Create a ToDo
     * @param newToDo the ToDo object to be created
     */
    @PostMapping
    @IsAllowedToCreate
    public ResponseEntity<Boolean> postToDO(@Valid @RequestBody ToDoCreateDTO newToDo){
        Boolean response = this.toDoServiceImpl.createToDo(this.modelMapper.map(newToDo, ToDo.class));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update a ToDo
     */
    @PutMapping
    @IsAllowedToUpdate
    public ResponseEntity<ToDo> putToDo(@Valid @RequestBody ToDoUpdateDTO newToDo){
        ToDo response = this.getToDoServiceImpl().updateToDo(this.getModelMapper().map(newToDo, ToDo.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete a ToDo
     */
    @DeleteMapping
    @IsAllowedToDelete
    public ResponseEntity<Void> deleteToDo(@Valid @RequestBody ToDoDeleteDTO newToDo){
        this.getToDoServiceImpl().deleteToDo(this.getModelMapper().map(newToDo, ToDo.class));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @IsAllowedToDelete
    public ResponseEntity<Void> deleteToDoById(@PathVariable Long id){
        this.getToDoServiceImpl().deleteToDoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @IsAllowedToRead
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id){
        return new ResponseEntity<>(this.getToDoServiceImpl().getToDoById(id), HttpStatus.OK);
    }

    /**
     * Read all ToDo objects
     * @return
     */
    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public ResponseEntity<List<ToDo>> getAllTodo(){
//        return this.getToDoServiceImpl().getAllToDo();
        return new ResponseEntity<>(this.getToDoServiceImpl().getAllToDo(), HttpStatus.OK);
    }


    @GetMapping(path = "/finished")
    @PreAuthorize("hasRole('TODO_READ_FINISHED')")
    public ResponseEntity<List<ToDo>> getAllFinishedTodo(){
//        return this.getToDoServiceImpl().getFinishedToDo();
        return new ResponseEntity<>(this.getToDoServiceImpl().getFinishedToDo(), HttpStatus.OK);
    }

    @GetMapping(path = "/open")
    @PreAuthorize("hasRole('TODO_READ_OPEN')")
    public ResponseEntity<List<ToDo>> getAllOpenTodo(){
//        return this.getToDoServiceImpl().getOpenToDo();
        return new ResponseEntity<>(this.getToDoServiceImpl().getOpenToDo(), HttpStatus.OK);
    }

    @GetMapping(path = "/numfinished")
    @PreAuthorize("hasRole('TODO_READ_COUNT_FINISHED')")
    public ResponseEntity<Long> getNumOfFinishedTodo(){
        return new ResponseEntity<>(this.getToDoServiceImpl().getNumOfFinishedToDo(), HttpStatus.OK);
//        return this.getToDoServiceImpl().getNumOfFinishedToDo();
    }

    @GetMapping(path = "/numopen")
    @PreAuthorize("hasRole('TODO_READ_COUNT_OPEN')")
    public ResponseEntity<Long> getNumOfOpenTodo(){
        return new ResponseEntity<>(this.getToDoServiceImpl().getNumOfOpenToDo(), HttpStatus.OK);
    }

    @GetMapping(path = "/numall")
    @PreAuthorize("hasRole('TODO_READ_COUNT')")
    public ResponseEntity<Long> getNumOfTodo(){
        return new ResponseEntity<>(this.getToDoServiceImpl().getNumOfToDo(), HttpStatus.OK);
    }
}
