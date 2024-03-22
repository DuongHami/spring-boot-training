package com.example.springboottraining;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.exception.ToDoNotFoundException;
import com.example.springboottraining.repository.ToDoRepository;
import com.example.springboottraining.service.ToDoServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoServiceImpl toDoService;

    @Test
    void createToDo() {
        ToDo notPersistedToDo = new ToDo(null, "Clean up room", "A", false);
        ToDo persistedToDo    = new ToDo(1L, "Clean up room", "A", false);

        when(toDoRepository.save(any(ToDo.class))).thenReturn(persistedToDo);

        assertEquals(persistedToDo, toDoService.createToDo(notPersistedToDo));
    }

    @Test
    void getToDoById() {
        ToDo toDo1 = new ToDo(null, "Clean up room", "A", false);
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.of(toDo1));

        assertEquals(toDo1, toDoService.getToDoById(1L));
    }

    @Test
    void getToDoByIdThrowsExeption() {
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ToDoNotFoundException.class, () -> toDoService.getToDoById(100L));
    }

}
