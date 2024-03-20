package com.example.springboottraining.database;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.repository.ToDoRepository;
import com.example.springboottraining.service.ToDoServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
public class DatabasePopulator implements CommandLineRunner {

    private final ToDoRepository toDoRepository;

    @Override
    public void run(String... args) throws Exception {
        ToDo t1 = new ToDo(0L, "Clean up room", "A", true);
        ToDo t2 = new ToDo(0L, "Go out with the dog", "B", true);
        ToDo t3 = new ToDo(0L, "Cook lunch", "C", false);
        ToDo t4 = new ToDo(0L, "Buy groceries", "D", false);
        ToDo t5 = new ToDo(0L, "Wash dishes", "E", false);

        ToDo t6 = new ToDo(0L, "Testing", "F", false);
        ToDo t3new = new ToDo(3L, "Cook lunch", "C", true);
        ToDo t4trash = new ToDo(4L, "Buy groceries", "D", false);

        this.getToDoRepository().saveAll(Arrays.asList(t1, t2, t3, t4, t5));

//        ToDoServiceImpl imp = new ToDoServiceImpl(this.getToDoRepository());
//        imp.createToDo(t6);
//
//        imp.updateToDo(t3new);
//        imp.deleteToDo(t4trash);
//
//        List<ToDo> all = imp.getAllToDo();
//        List<ToDo> finished = imp.getFinishedToDo();
//        List<ToDo> open = imp.getOpenToDo();
//
//        Long numFinished = imp.getNumOfFinishedToDo();
//        Long numOpen = imp.getNumOfOpenToDo();




    }
}
