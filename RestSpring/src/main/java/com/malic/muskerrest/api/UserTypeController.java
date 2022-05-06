package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.userType.UserTypeDao;
import com.malic.muskerrest.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/usertype")
public class UserTypeController {

    @Autowired
    private UserTypeDao userTypeDao;

    @GetMapping("/all")
    public List<UserType> getAllUserType(){
        return userTypeDao.getAllUserTypes();
    }

    @GetMapping("/type/{id}")
    public UserType getUserType(@PathVariable int id){
        return userTypeDao.getUserType(id);
    }
}
