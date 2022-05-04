package com.malic.musker.dao.user;

import com.malic.musker.entities.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();
    public User getUser(long id);
    public void editUser(User user);
    public void deleteUser(long id);
    public void deleteUser(User user);
    public void addUser(User user);

}
