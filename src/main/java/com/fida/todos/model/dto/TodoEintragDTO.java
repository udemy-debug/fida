package com.fida.todos.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class TodoEintragDTO {

    @JsonProperty("id")
    private Long id;

    @NotEmpty
    @Size(max = 20)
    @JsonProperty("beschreibung")
    private String beschreibung;

    @NotEmpty
    @JsonProperty("erledigungsDatum")
    private LocalDate erledigungsDatum;

    @Builder
    public TodoEintragDTO(Long id, String beschreibung, LocalDate erledigungsDatum) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.erledigungsDatum = erledigungsDatum;
    }

}
