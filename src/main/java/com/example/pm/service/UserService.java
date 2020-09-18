package com.example.pm.service;

import com.example.pm.entity.User;

import java.util.List;

public interface UserService {
    public Object login(String username, String password);
    public User getUserById(Long id);
    public User getUserByUsername(String username);
    public List<User> getAllUser();
    public void insertUser(User user);
    public void deleteUser(User user);
    public void updateUser(User user);
}
