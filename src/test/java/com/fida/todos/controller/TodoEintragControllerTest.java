package com.fida.todos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fida.todos.TodoEintragApplication;
import com.fida.todos.exception.AppNotFoundException;
import com.fida.todos.model.dto.TodoEintragDTO;
import com.fida.todos.service.TodoEintragService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static com.fida.todos.utils.TodoEintragUtils.createTodoEintragDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = TodoEintragApplication.class)
class TodoEintragControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoEintragService service;

    @Captor
    private ArgumentCaptor<TodoEintragDTO> argumentCaptor;

    private final static String urlTemplate = "/api/v1/todos";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    @DisplayName("Sollte erfolgreich einen neuen Eintrag erstellen")
    public void sollteErfolgreichEinNeuenEintragErstellen() throws Exception {
        LocalDate date = LocalDate.now();
        Optional<TodoEintragDTO> dto = Optional.of(createTodoEintragDto(1L,"beschreibung", date));

        when(service.createTodoEintrag(argumentCaptor.capture())).thenReturn(1L);

        mockMvc.perform(post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/v1/todos/1"));

        assertThat(argumentCaptor.getValue().getId(), is(1L));
        assertThat(argumentCaptor.getValue().getBeschreibung(), is("beschreibung"));
        assertThat(argumentCaptor.getValue().getErledigungsDatum().toString(), is(LocalDate.now().toString()));
    }

    @Test
    @Order(2)
    @DisplayName("Sollte erfolgreich zwei neuen Eintrage erstellen")
    public void sollteErfolgreichZweiNeuenEintrageErstellen() throws Exception {
        LocalDate date = LocalDate.now();
        List<Optional<TodoEintragDTO>> stub = Arrays.asList(
                        createTodoEintragDto(1L,"beschreibung1", date),
                        createTodoEintragDto(2L, "beschreibung2", date))
                .stream()
                .map(Optional::of)
                .collect(Collectors.toList());

        when(service.getAllTodoEintrage()).thenReturn(stub);

        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].beschreibung", is("beschreibung1")))
                .andExpect(jsonPath("$[0].erledigungsDatum", is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].beschreibung", is("beschreibung2")))
                .andExpect(jsonPath("$[1].erledigungsDatum", is(LocalDate.now().toString())));
    }

    @Test
    @DisplayName("Eintrag-by-ID sollte erfolgreich abgerufen werden")
    public void TodoEintragbyIdsSollteErfolgreichAbgerufenWerden() throws Exception {
        Optional<TodoEintragDTO> stub = Optional.of(createTodoEintragDto(1L,"beschreibung1", LocalDate.now()));

        when(service.getTodoEintragById(1L)).thenReturn(stub);

        mockMvc.perform(get(urlTemplate + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.beschreibung", is("beschreibung1")))
                .andExpect(jsonPath("$.erledigungsDatum", is(LocalDate.now().toString())));
    }

    @Test
    @DisplayName("Sollte AppNotFoundException mit dem Fehler „Fehler nicht gefunden“ erhalten. HTTP-Status")
    public void sollteAppNotFoundExceptionWithErrorNotFoundHttpStatus() throws Exception {
        when(service.getTodoEintragById(1L))
                .thenThrow(new AppNotFoundException("No such App for id '1'"));

        mockMvc.perform(get(urlTemplate + "/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("TodoEintrag sollte erfolgreich mit bekannter ID aktualisiert werden.")
    public void sollteUpdateTodoEintragMitBekannterIdErfolgreich() throws Exception {
        LocalDate datum = LocalDate.now();
        Optional<TodoEintragDTO> stub = Optional.of(createTodoEintragDto(1L,"beschreibung1", datum));
        TodoEintragDTO dto = createTodoEintragDto(1L,"beschreibung1", datum);

        when(service.updateTodoEintrag(eq(1L), argumentCaptor.capture())).thenReturn(stub);

        mockMvc.perform(put(urlTemplate + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThat(argumentCaptor.getValue().getId(), is(1L));
        assertThat(argumentCaptor.getValue().getBeschreibung(), is("beschreibung1"));
        assertThat(argumentCaptor.getValue().getErledigungsDatum().toString(), is(LocalDate.now().toString()));
    }

    @Test
    @DisplayName("Should Try Update App With Unknown Id With Error NotFound Http Status")
    public void shouldTryUpdateAppWithUnknownIdWithErrorNotFoundHttpStatus() throws Exception {
        LocalDate datum = LocalDate.now();
        TodoEintragDTO dto = createTodoEintragDto(1L,"beschreibung1", datum);

        when(service.updateTodoEintrag(eq(42L), argumentCaptor.capture()))
                .thenThrow(new AppNotFoundException("Keine solche Eintrag für ID „42“"));

        mockMvc.perform(put(urlTemplate + "/42")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}