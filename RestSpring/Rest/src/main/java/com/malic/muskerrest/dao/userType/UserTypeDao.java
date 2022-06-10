package com.malic.muskerrest.dao.userType;

import com.malic.muskerrest.entities.UserType;

import java.util.List;

public interface UserTypeDao {

    List<UserType> getAllUserTypes();
    UserType getUserType(int id);
    void editUserType(UserType userType);
    void deleteUserType(int id);
    void deleteUserType(UserType userType);
    void addUserType(UserType userType);

}
