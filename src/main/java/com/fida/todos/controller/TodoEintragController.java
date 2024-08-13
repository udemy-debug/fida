package com.fida.todos.controller;

import com.fida.todos.model.dto.TodoEintragDTO;
import com.fida.todos.service.TodoEintragService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoEintragController {

  private final TodoEintragService service;

  /**
   * Erstellt einen neuen Aufgabe
   * @param dto
   * @param uriComponentsBuilder
   * @return Response code
   */
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody TodoEintragDTO dto, UriComponentsBuilder uriComponentsBuilder) {
    Long appId = service.createTodoEintrag(dto);
    UriComponents uriComponents = uriComponentsBuilder
            .path("/api/v1/todos/{id}")
            .buildAndExpand(appId);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriComponents.toUri());
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  /**
   * Sammelt alle Aufgaben
   * @return Response code
   */
  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(service.getAllTodoEintrage());
  }

  /**
   * Ruft eine bestimmte Aufgabe ab
   * @param id
   * @return Response code
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getTodoEintragById(id));
  }

  /**
   * Führt ein Update zu einer bestimmten Aufgabe durch
   * @param id
   * @param dto
   * @return Response code
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TodoEintragDTO dto) {
    service.updateTodoEintrag(id, dto);
    return ResponseEntity.noContent().build();
  }

  /**
   * Löscht eine bestimmte Aufgabe
   * @param id
   * @return Response code
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    service.deleteTodoEintragById(id);
    return ResponseEntity.ok().build();
  }

}
