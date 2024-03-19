package com.example.springboottraining.database;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.repository.ToDoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Getter
public class DatabasePopulator implements CommandLineRunner {

    private final ToDoRepository toDoRepository;

    @Override
    public void run(String... args) throws Exception {
        ToDo t1 = new ToDo(0L, "Clean up room", "A", false);
        ToDo t2 = new ToDo(0L, "Go out with the dog", "B", false);
        ToDo t3 = new ToDo(0L, "Cook lunch", "C", false);
        ToDo t4 = new ToDo(0L, "Buy groceries", "D", false);
        ToDo t5 = new ToDo(0L, "Wash dishes", "E", false);

        this.getToDoRepository().saveAll(Arrays.asList(t1, t2, t3, t4, t5));

    }
}
