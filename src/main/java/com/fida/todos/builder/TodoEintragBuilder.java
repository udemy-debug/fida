package com.fida.todos.builder;

import com.fida.todos.model.domain.TodoEintrag;
import com.fida.todos.model.dto.TodoEintragDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TodoEintragBuilder {

    @Autowired
    private final ModelMapper modelMapper;

    public TodoEintrag build(TodoEintragDTO dto) {
        dto.setErledigungsDatum(LocalDate.now());
        TodoEintrag model = modelMapper.map(dto, TodoEintrag.class);
        return model;
    }

    public Optional<TodoEintragDTO> build(TodoEintrag domain) {
        TodoEintragDTO dto = modelMapper.map(domain, TodoEintragDTO.class);
        return Optional.of(dto);
    }

    public TodoEintrag build(TodoEintragDTO dto, TodoEintrag domain) {
        dto.setErledigungsDatum(LocalDate.now());
        modelMapper.map(dto, domain);
        return domain;
    }
}
