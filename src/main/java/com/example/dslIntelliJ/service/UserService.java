package com.example.dslIntelliJ.service;

import com.example.dslIntelliJ.entity.User;
import com.example.dslIntelliJ.respository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserService(UserRepository userRepository, EntityManager entityManager) { // , EntityManager entityManager
        this.userRepository = userRepository;
        this.queryFactory = new JPAQueryFactory(entityManager); // EntityManager를 통해 queryFactory 초기화
    }

    /* 회원가입 */
    public User saveUser(User user) {
        // 이메일 중복 체크
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 사용자 저장
        return userRepository.save(user);
    }

    /* 회원 조회(전체) */
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }


    /* 회원 조회(단일) */
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /* 정보 수정 */
    public User updateUser(Long id, User UserDetails) {
        Optional<User> UserOptional = userRepository.findById(id);
        if (UserOptional.isPresent()) {
            User User = UserOptional.get();
            User.setPassword(UserDetails.getPassword());
            User.setUsername(UserDetails.getUsername());
            User.setEmail(UserDetails.getEmail());
            User.setAge(UserDetails.getAge());
            User.setGender(UserDetails.getGender());

            return userRepository.save(User);
        } else {
            return null;
        }
    }

    /* 회원 삭제 */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /* QueryDsl - User 조건 조회 */
    public List<User> searchUsers(String username, String email, Integer age, String gender){
        return userRepository.searchUsers(username, email, age, gender);
    }
}
