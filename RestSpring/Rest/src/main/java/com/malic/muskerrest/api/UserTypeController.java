package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.userType.UserTypeDao;
import com.malic.muskerrest.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserType>> getAllUserType(){
        return ResponseEntity.ok(userTypeDao.getAllUserTypes());
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<UserType> getUserType(@PathVariable int id){
        return ResponseEntity.ok(userTypeDao.getUserType(id));
    }
}
