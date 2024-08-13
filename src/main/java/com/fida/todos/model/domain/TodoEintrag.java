package com.fida.todos.model.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@NoArgsConstructor
@Table(name = "eintrage")
public class TodoEintrag {

  @Id
  @GeneratedValue
  private Long id;
  private String beschreibung;
  private LocalDate erledigungsdatum;

  @Builder
  public TodoEintrag(Long id, String beschreibung, LocalDate erledigungsdatum) {
    this.id = id;
    this.beschreibung = beschreibung;
    this.erledigungsdatum = erledigungsdatum;
  }

}
