package com.example.springboottraining;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ToDoRepositoryTest {

    @Autowired
    private ToDoRepository repository;
    @Test
    void saveToDo(){
        ToDo todo = new ToDo(0L, "Testen", "Test-Beschreibung", false);
        ToDo response = repository.save(todo);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getTaskname()).isEqualTo("Testen");
        assertThat(response.getDescription()).isEqualTo("Test-Beschreibung");
        assertThat(response.getCompleted()).isFalse();

    }

    @Test
    void deleteToDo(){
        ToDo todo = new ToDo(0L, "Testen", "Test-Beschreibung", false);
        ToDo response = repository.save(todo);

        repository.delete(response);

        assertThat(repository.existsById(1L)).isFalse();
    }

    @Test
    void findByCompletedFalse(){
        saveToDo();
        List<ToDo> todoList = repository.findByCompleted(false);

        assertThat(todoList.size()).isEqualTo(1);
    }

    @Test
    void findByCompletedTrue(){
        saveToDo();
        List<ToDo> todoList = repository.findByCompleted(true);

        assertThat(todoList.size()).isEqualTo(0);
    }

    @Test
    void findNumCompletedTrue(){
        saveToDo();
        Long num = repository.countAllByCompletedIsTrue();

        assertThat(num).isEqualTo(0);
    }

    @Test
    void findNumCompletedFalse(){
        saveToDo();
        Long num = repository.countAllByCompletedIsFalse();

        assertThat(num).isEqualTo(1);
    }
}
