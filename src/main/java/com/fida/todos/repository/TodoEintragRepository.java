package com.fida.todos.repository;

import com.fida.todos.model.domain.TodoEintrag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoEintragRepository extends JpaRepository<TodoEintrag, Long> {
}
