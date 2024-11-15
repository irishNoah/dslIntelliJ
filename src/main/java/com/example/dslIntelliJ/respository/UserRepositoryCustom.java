package com.example.dslIntelliJ.respository;

import com.example.dslIntelliJ.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> searchUsers(String username, String email, Integer age, String gender);
}
