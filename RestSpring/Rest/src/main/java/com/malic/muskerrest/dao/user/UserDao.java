package com.malic.muskerrest.dao.user;

import com.malic.muskerrest.entities.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User getUser(long id);
    void editUser(User user);
    void deleteUser(long id);
    void deleteUser(User user);
    void addUser(User user);
    User getUserByUsername(String username);
    List<User> getUsersByUserType(int id);
}
