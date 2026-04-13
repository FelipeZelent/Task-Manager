package com.taskmanager.taskmanager.application.usecase;

import com.taskmanager.taskmanager.application.port.in.CreateTaskCommand;
import com.taskmanager.taskmanager.application.port.in.UpdateTaskCommand;
import com.taskmanager.taskmanager.application.port.out.TaskGateway;
import com.taskmanager.taskmanager.domain.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskInteractorTest {
    @Mock
    private TaskGateway taskGateway;

    @InjectMocks
    private TaskInteractor taskInteractor;

    @Test
    void shouldCreateTaskThroughGateway() {
        when(taskGateway.save(any(Task.class))).thenAnswer(invocation -> {
            Task task = invocation.getArgument(0);
            return new Task(1L, task.getTitle(), task.getDescription(), task.isCompleted());
        });

        Task createdTask = taskInteractor.create(
                new CreateTaskCommand("Estudar", "Arquitetura limpa", false)
        );

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskGateway).save(captor.capture());
        Task taskToSave = captor.getValue();

        assertThat(taskToSave.getId()).isNull();
        assertThat(taskToSave.getTitle()).isEqualTo("Estudar");
        assertThat(createdTask.getId()).isEqualTo(1L);
    }

    @Test
    void shouldUpdateExistingTask() {
        when(taskGateway.findById(1L)).thenReturn(Optional.of(
                new Task(1L, "Antigo", "Descricao antiga", false)
        ));
        when(taskGateway.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Task> updatedTask = taskInteractor.update(
                1L,
                new UpdateTaskCommand("Novo", "Descricao nova", true)
        );

        assertThat(updatedTask).isPresent();
        assertThat(updatedTask.get().getId()).isEqualTo(1L);
        assertThat(updatedTask.get().getTitle()).isEqualTo("Novo");
        assertThat(updatedTask.get().isCompleted()).isTrue();
    }

    @Test
    void shouldReturnEmptyWhenUpdatingMissingTask() {
        when(taskGateway.findById(99L)).thenReturn(Optional.empty());

        Optional<Task> updatedTask = taskInteractor.update(
                99L,
                new UpdateTaskCommand("Novo", "Descricao nova", true)
        );

        assertThat(updatedTask).isEmpty();
        verify(taskGateway, never()).save(any(Task.class));
    }

    @Test
    void shouldDeleteTaskOnlyWhenItExists() {
        when(taskGateway.existsById(1L)).thenReturn(true);

        boolean deleted = taskInteractor.deleteById(1L);

        assertThat(deleted).isTrue();
        verify(taskGateway).deleteById(1L);
    }
}
