package com.fida.todos.utils;

import com.fida.todos.model.dto.TodoEintragDTO;

import java.time.LocalDate;

public class TodoEintragUtils {
    public static TodoEintragDTO createTodoEintragDto(Long id, String Beschreibung, LocalDate erledigungsDatum) {
        return TodoEintragDTO.builder()
                .id(id)
                .beschreibung(Beschreibung)
                .erledigungsDatum(erledigungsDatum)
                .build();
    }
}
