package com.taskmanager.taskmanager.infrastructure.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.taskmanager.infrastructure.persistence.entity.TaskEntity;
import com.taskmanager.taskmanager.infrastructure.persistence.repository.SpringDataTaskRepository;
import com.taskmanager.taskmanager.infrastructure.web.dto.TaskRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringDataTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateAndFetchTask() throws Exception {
        TaskRequest request = new TaskRequest("Estudar Spring", "Separar camadas", false);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Estudar Spring"))
                .andExpect(jsonPath("$.description").value("Separar camadas"))
                .andExpect(jsonPath("$.completed").value(false));

        TaskEntity savedTask = repository.findAll().get(0);

        mockMvc.perform(get("/api/tasks/{id}", savedTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedTask.getId()))
                .andExpect(jsonPath("$.title").value("Estudar Spring"));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingMissingTask() throws Exception {
        TaskRequest request = new TaskRequest("Atualizada", "Descricao", true);

        mockMvc.perform(put("/api/tasks/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteExistingTask() throws Exception {
        TaskEntity task = new TaskEntity();
        task.setTitle("Remover");
        task.setDescription("Apagar tarefa");
        task.setCompleted(false);
        TaskEntity savedTask = repository.save(task);

        mockMvc.perform(delete("/api/tasks/{id}", savedTask.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/tasks/{id}", savedTask.getId()))
                .andExpect(status().isNotFound());
    }
}
