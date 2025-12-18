package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface TodoCustomRepository {
    Todo findTodoByIdWithUser(long todoId);

    Page<TodoSearchResponse> search(String query, LocalDateTime start, LocalDateTime end, Pageable pageable);
}
