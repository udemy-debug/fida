package com.fida.todos.builder;

import com.fida.todos.model.domain.TodoEintrag;
import com.fida.todos.model.dto.TodoEintragDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TodoEintragBuilderTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TodoEintragBuilder builder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void build() {
    }

    @Test
    void testBuild() {
    }

    @Test
    void testBuild1() {
    }
}