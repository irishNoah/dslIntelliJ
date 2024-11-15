package com.example.dslIntelliJ.controller;

import com.example.dslIntelliJ.entity.User;
import com.example.dslIntelliJ.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /* 등록 */
    @PostMapping("/register-user")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /* 조회(전체) */
    @GetMapping("/get-user")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    /* 조회(단일) */
    @Transactional
    @GetMapping("/get-user/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        System.out.println("User Find is... 또6 : " + user);

        return user;
    }

    /* 수정 */
    @Transactional
    @PutMapping("/put-user/{id}")
    public ResponseEntity<User> updateMember(@PathVariable("id") Long id, @RequestBody User userDetails) {
        User updateUser = userService.updateUser(id, userDetails);
        if (userDetails != null) {
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* 삭제 */
    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search-user-querydsl")
    public List<User> searchUsers(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "gender", required = false) String gender){
        return userService.searchUsers(username, email, age, gender);
    }
}