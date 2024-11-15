package com.example.dslIntelliJ.respository;

import com.example.dslIntelliJ.entity.QUser;
import com.example.dslIntelliJ.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    // QUser 인스턴스를 클래스 필드로 선언 for BooleanExpression
    private static final QUser user = QUser.user;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    /* BooleanExpression 사용 */
    // https://velog.io/@couchcoding/Spring-QueryDSL%EB%A1%9C-%EC%A1%B0%EA%B1%B4-%EA%B2%80%EC%83%89-API%EB%A5%BC-%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EC%9E%90%EB%8F%99%EC%A0%81-%EC%BF%BC%EB%A6%AC
    @Override
    public List<User> searchUsers(String username, String email, Integer age, String gender) {
        return queryFactory.selectFrom(user)
                .where(containsUsername(username), containsEmail(email), goeAge(age), eqGender(gender))
                .fetch();
    }

    private BooleanExpression containsUsername(String usernameCond) {
        return usernameCond != null ? user.username.contains(usernameCond) : null;
    }

    private BooleanExpression containsEmail(String emailCond) {
        return emailCond != null ? user.email.contains(emailCond) : null;
    }

    private BooleanExpression goeAge(Integer ageCond) {
        return ageCond != null ? user.age.goe(ageCond) : null;
    }

    private BooleanExpression eqGender(String genderCond) {
        return genderCond != null ? user.gender.eq(genderCond) : null;
    }

}
