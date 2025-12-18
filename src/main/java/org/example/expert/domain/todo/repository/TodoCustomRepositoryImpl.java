package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.entity.QComment;
import org.example.expert.domain.manager.entity.QManager;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

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

    @Override
    public Page<TodoSearchResponse> search(String query, LocalDateTime start, LocalDateTime end, Pageable pageable) {

        QTodo todo = QTodo.todo;
        QManager manager = QManager.manager;
        QUser user = QUser.user;
        QComment comment = QComment.comment;

        BooleanExpression keywordCondition = getKeywordCondition(query, todo, user);

        List<TodoSearchResponse> todos = queryFactory
                .select(Projections.constructor(TodoSearchResponse.class,
                        todo.title,
                        manager.id.countDistinct(),
                        comment.id.countDistinct()
                ))
                .from(todo)
                .leftJoin(todo.managers, manager).on(manager.todo.id.eq(todo.id))
                .leftJoin(manager.user,user).on(manager.user.id.eq(user.id))
                .leftJoin(todo.comments, comment).on(todo.id.eq(comment.todo.id))
                .where(
                        createdBetween(start, end),
                        keywordCondition
                )
                .groupBy(todo.id)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(todo.id.countDistinct())
                .from(todo)
                .leftJoin(todo.managers, manager)
                .leftJoin(manager.user, user)
                .where(
                        createdBetween(start, end),
                        keywordCondition
                )
                .fetchOne();

        if (total == null) total = 0L;

        return new PageImpl<>(todos, pageable, total);
    }

    @Nullable
    private static BooleanExpression getKeywordCondition(String query, QTodo todo, QUser user) {
        BooleanExpression keywordCondition = null;
        if (query != null && !query.isBlank()) {
            BooleanExpression titleCondition = todo.title.contains(query);
            BooleanExpression nicknameCondition = user.nickname.contains(query);
            keywordCondition = titleCondition.or(nicknameCondition);
        }
        return keywordCondition;
    }

    private BooleanExpression createdBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null && end == null) return null;
        if (start == null) return todo.createdAt.loe(end);
        if (end == null) return todo.createdAt.goe(start);
        return todo.createdAt.between(start, end);
    }
}
