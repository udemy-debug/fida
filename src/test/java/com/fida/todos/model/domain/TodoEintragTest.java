package com.fida.todos.model.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TodoEintragTest {

    TodoEintrag eintrag = new TodoEintrag();
    @BeforeEach
    void setUp() {
        eintrag.setId(1L);
        eintrag.setErledigungsdatum(LocalDate.now());
        eintrag.setBeschreibung("beschreibung");
    }

    @Test
    void getId() {
        assertThat(eintrag.getId(), is(1L));
    }

    @Test
    void getBeschreibung() {
        assertThat(eintrag.getBeschreibung(), is("beschreibung"));
    }

    @Test
    void getErledigungsdatum() {
        assertThat(eintrag.getErledigungsdatum().toString(), is(LocalDate.now().toString()));
    }

    @Test
    void setId() {
        eintrag.setId(55L);
        assertThat(eintrag.getId(), is(55L));
    }

    @Test
    void setBeschreibung() {
        eintrag.setBeschreibung("beschreibung55");
        assertThat(eintrag.getBeschreibung(), is("beschreibung55"));
    }

    @Test
    void setErledigungsdatum() {
        LocalDate datum = LocalDate.of(2013,1, 26);
        eintrag.setErledigungsdatum(datum);
        assertThat(eintrag.getErledigungsdatum().toString(), is(datum.toString()));
    }
}