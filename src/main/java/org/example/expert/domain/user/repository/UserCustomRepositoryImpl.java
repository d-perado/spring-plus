package org.example.expert.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{

    private final JPAQueryFactory queryFactory;
}
