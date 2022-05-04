package com.malic.musker.dao.userType;

import com.malic.musker.entities.UserType;

import java.util.List;

public interface UserTypeDao {

    public List<UserType> getAllUserTypes();
    public UserType getUserType(int id);
    public void editUserType(UserType userType);
    public void deleteUserType(int id);
    public void deleteUserType(UserType userType);
    public void addUserType(UserType userType);

}
