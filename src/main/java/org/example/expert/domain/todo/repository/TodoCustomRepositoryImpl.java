package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.QUser;
import org.example.expert.domain.user.entity.User;

@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Todo findTodoByIdWithUser(long todoId) {
        QUser user = QUser.user;
        QTodo todo = QTodo.todo;

        Todo findedTodo = queryFactory.select(todo)
                .from(todo)
                .join(todo.user, user)
                .fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        if (findedTodo == null) {
            throw new IllegalArgumentException("할 일을 찾을수 없습니다 ^^");
        }

        return findedTodo;
    }
}
