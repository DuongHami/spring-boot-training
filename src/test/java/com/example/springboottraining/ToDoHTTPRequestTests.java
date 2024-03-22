package com.example.springboottraining;

import com.example.springboottraining.entity.ToDo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest
public class ToDoHTTPRequestTests {

    @Test
    void getToDoTest(){
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<ToDo> response = restTemplate.getForEntity("http://localhost:8080/todo/5", ToDo.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ToDo.class, response.getBody().getClass());
        assertEquals(5, response.getBody().getId());
    }

    @Test
    void createToDoTestSuccess(){
        TestRestTemplate restTemplate = new TestRestTemplate();
        ToDo newTodo = new ToDo(0L, "Testen", "Test-Beschreibung", false);

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ToDo> requestEntity = new HttpEntity<>(newTodo, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/todo", requestEntity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createToDoTestError(){
        TestRestTemplate restTemplate = new TestRestTemplate();
        ToDo newTodo = new ToDo();

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ToDo> requestEntity = new HttpEntity<>(newTodo, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/todo", requestEntity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteToDo(){
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.delete("http://localhost:8080/5");

//        ResponseEntity<ToDo> response = restTemplate.getForEntity("http://localhost:8080/todo/5", ToDo.class);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getOpenToDo(){
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<List<ToDo>> response = restTemplate.exchange(
                "http://localhost:8080/todo/open",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
        });

        List<ToDo> todos = response.getBody();
        if(!todos.isEmpty()){
            for(ToDo todo : todos){
                assertEquals(false, todo.getCompleted());
            }
        }
    }



}
