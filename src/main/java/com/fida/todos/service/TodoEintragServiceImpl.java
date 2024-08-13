package com.fida.todos.service;

import com.fida.todos.builder.TodoEintragBuilder;
import com.fida.todos.exception.AppNotFoundException;
import com.fida.todos.model.domain.TodoEintrag;
import com.fida.todos.model.dto.TodoEintragDTO;
import com.fida.todos.repository.TodoEintragRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TodoEintragServiceImpl implements TodoEintragService {

    private final TodoEintragRepository repository;
    private final TodoEintragBuilder builder;

    @Override
    public Long createTodoEintrag(TodoEintragDTO dto) {
        return Stream.of(dto)
                .map(builder::build)
                .map(repository::save)
                .map(TodoEintrag::getId)
                .findFirst()
                .get();
    }

    @Override
    public List<Optional<TodoEintragDTO>> getAllTodoEintrage() {
        return repository.findAll()
                .stream()
                .map(builder::build)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TodoEintragDTO> getTodoEintragById(Long id) {
        return repository.findById(id)
                .map(builder::build)
                .orElseThrow(() -> new AppNotFoundException(String.format("No such App for id '%s'", id)));
    }

    @Override
    public void deleteTodoEintragById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<TodoEintragDTO> updateTodoEintrag(Long id, TodoEintragDTO dto) {
        return repository.findById(id)
                .map(model -> builder.build(dto, model))
                .map(repository::save)
                .map(builder::build)
                .orElseThrow(() -> new AppNotFoundException(String.format("No such App for id '%s'", id)));
    }
}
