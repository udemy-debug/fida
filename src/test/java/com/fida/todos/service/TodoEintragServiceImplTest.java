package com.fida.todos.service;

import com.fida.todos.builder.TodoEintragBuilder;
import com.fida.todos.model.dto.TodoEintragDTO;
import com.fida.todos.repository.TodoEintragRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
class TodoEintragServiceImplTest {
    @Mock
    private TodoEintragRepository mockRepository;
    @Mock
    private TodoEintragBuilder mockBuilder;
    @InjectMocks
    private TodoEintragServiceImpl mockService;
    @BeforeAll
    public static void beforeAll() {
      MockitoAnnotations.openMocks(TodoEintragServiceImplTest.class);
    }

    /**
     *
     */
//    @Test
//    void ErstelEinNeueEintrag() {
//        TodoEintragDTO dto = TodoEintragUtils.createTodoEintragDto(1L,"beschreibung", LocalDate.now());
//        Long response = mockService.createTodoEintrag(dto);
//        assert(response == 1);
//    }

    @Test
    void SammelalleTodoEintrage() {
        List<Optional<TodoEintragDTO>> response = mockService.getAllTodoEintrage();
        assert(!response.isEmpty());
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