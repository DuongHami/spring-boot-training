package com.example.springboottraining;

import com.example.springboottraining.entity.ToDo;
import com.example.springboottraining.exception.ToDoNotFoundException;
import com.example.springboottraining.service.ToDoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ToDoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ToDoServiceImpl service;

    ToDo t1 = new ToDo(1L, "Clean up room", "A", true);
    ToDo t2 = new ToDo(2L, "Go out with the dog", "B", true);
    ToDo t3 = new ToDo(3L, "Cook lunch", "C", false);
    ToDo t4 = new ToDo(4L, "Buy groceries", "D", false);
    ToDo t5 = new ToDo(5L, "Wash dishes", "E", false);

    List<ToDo> open = Arrays.asList(t3, t4, t5);
    List<ToDo> finished = Arrays.asList(t1, t2);
    List<ToDo> all = Arrays.asList(t1, t2, t3, t4, t5);


    @Test
    @WithMockUser(roles = "TODO_CREATE")
    public void createToDo() throws Exception{
        when(service.createToDo(t1)).thenReturn(true);

        mvc.perform(post("/todo")
//                        .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(t1)))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(roles = {"TODO_UPDATE"})
    public void updateToDo() throws Exception {
        ToDo t1 = new ToDo(4L, "Clean up room", "A", true);
        when(service.updateToDo(any(ToDo.class))).thenReturn(t1);

        mvc.perform(put("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(t1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed", is(true)));

    }

    @Test
    @WithMockUser(roles = {"TODO_DELETE"})
    public void deleteToDo() throws Exception {

        mvc.perform(delete("/todo/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());

    }

//    @Test
////    @WithMockUser(roles = {"TODO_READ"})
//    public void deleteToDoUnauthorized() throws Exception {
//
//        mvc.perform(delete("/todo/{id}", 1L)
////                        .with(httpBasic("user", "password"))
//                        .contentType(MediaType.APPLICATION_JSON)
////                        .with(csrf())
//                )
//                .andExpect(status().isUnauthorized());
//
//    }

    @Test
    public void getToDoById() throws Exception {

        when(service.getToDoById(1L)).thenReturn(t1);

        mvc.perform(get("/todo/{id}", 1L)
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));

        verify(service, times(1)).getToDoById(1L);

    }

    @Test
    public void getToDoUnautherized() throws Exception {
        when(service.getToDoById(1L)).thenReturn(t1);
        mvc.perform(get("/todo/{id}", 1L)).andExpect(status().isUnauthorized());
    }

    @Test
    public void getToDoByIdError() throws Exception {
        when(service.getToDoById(100L)).thenThrow(ToDoNotFoundException.class);

        mvc.perform(get("/todo/{id}", 100L).with(httpBasic("user", "password")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("Die Entit�t konnte nicht gefunden werden")));

        verify(service, times(1)).getToDoById(100L);
    }

    @Test
    public void getAllTodo() throws Exception {
        when(service.getAllToDo()).thenReturn(all);

        mvc.perform(get("/todo/all").with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(all)))
                .andExpect(status().isOk());
    }

    @Test
    public void getOpenTodo() throws Exception {
        when(service.getOpenToDo()).thenReturn(open);

        mvc.perform(get("/todo/open").with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].completed", is(false)))
                .andExpect(jsonPath("$[1].completed", is(false)))
                .andExpect(jsonPath("$[2].completed", is(false)));
        verify(service, times(1)).getOpenToDo();
    }

    @Test
    public void getFinishedTodo() throws Exception {
        when(service.getFinishedToDo()).thenReturn(finished);

        mvc.perform(get("/todo/finished").with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].completed", is(true)))
                .andExpect(jsonPath("$[1].completed", is(true)));

        verify(service, times(1)).getFinishedToDo();
    }

    @Test
    public void numFinished() throws Exception{
        when(service.getNumOfFinishedToDo()).thenReturn(5L);

        mvc.perform(get("/todo/numfinished").with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(5)));

        verify(service, times(1)).getNumOfFinishedToDo();
    }

    @Test
    public void numOpen() throws Exception{
        when(service.getNumOfOpenToDo()).thenReturn(10L);

        mvc.perform(get("/todo/numopen").with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(10)));

        verify(service, times(1)).getNumOfOpenToDo();
    }

}
