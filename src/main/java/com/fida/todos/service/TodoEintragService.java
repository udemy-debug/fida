package com.fida.todos.service;

import com.fida.todos.model.dto.TodoEintragDTO;

import java.util.List;
import java.util.Optional;

public interface TodoEintragService {

    Long createTodoEintrag(TodoEintragDTO dto);

    List<Optional<TodoEintragDTO>> getAllTodoEintrage();

    Optional<TodoEintragDTO> getTodoEintragById(Long id);

    void deleteTodoEintragById(Long id);

    Optional<TodoEintragDTO> updateTodoEintrag(Long id, TodoEintragDTO dto);
}
