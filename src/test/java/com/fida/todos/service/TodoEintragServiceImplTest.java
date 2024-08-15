package com.fida.todos.service;

import com.fida.todos.builder.TodoEintragBuilder;
import com.fida.todos.model.domain.TodoEintrag;
import com.fida.todos.model.dto.TodoEintragDTO;
import com.fida.todos.repository.TodoEintragRepository;
import com.fida.todos.utils.TodoEintragUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TodoEintragServiceImplTest {

    @InjectMocks
    TodoEintragServiceImpl service;

    @Mock
    TodoEintragRepository repository;

    @Mock
    private TodoEintragBuilder builder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ErstelEinNeueEintrag() {
        TodoEintragDTO dto = TodoEintragUtils.createTodoEintragDto(1L,"beschreibung", LocalDate.now());
        TodoEintrag entity = new TodoEintrag(1l,"bescheibung", LocalDate.now());

        when(builder.build(Mockito.any(TodoEintragDTO.class))).thenReturn(entity);
        when(repository.save(Mockito.any(TodoEintrag.class))).thenReturn(entity);

        // service.createTodoEintrag(dto);

        // verify(repository, times(1)).save(entity);
    }

    @Test
    void SammelalleTodoEintrage() {
        List<TodoEintrag> list = new ArrayList<>();
        TodoEintrag empOne = new TodoEintrag(1l,"Beschreibung1", LocalDate.now());
        TodoEintrag empTwo = new TodoEintrag(2l,"Beschreibung2", LocalDate.now());
        TodoEintrag empThree = new TodoEintrag(3l,"Beschreibung3", LocalDate.now());

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(repository.findAll()).thenReturn(list);
       // when(builder.build(Mockito.any(TodoEintragDTO.class))).thenReturn(entity);

        //test
        List<Optional<TodoEintragDTO>> response = service.getAllTodoEintrage();
        assertEquals(3, response.size());
        verify(repository, times(1)).findAll();
        assert(response.isEmpty());
    }

    @Test
    void getTodoEintragById() {
    }

    @Test
    void deleteTodoEintragById() {
    }

    @Test
    void updateTodoEintrag() {
    }

}