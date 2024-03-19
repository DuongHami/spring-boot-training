package com.example.springboottraining.controller;

import com.example.springboottraining.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public String hello(){
        return "Hello";
    }

    @GetMapping("/{num}")
    public String helloWithNum(@PathVariable("num") Integer num){
        return "Hello " + num;
    }
}
