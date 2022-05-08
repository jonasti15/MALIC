package com.malic.muskerrest.api;

import com.malic.muskerrest.dao.user.UserDao;
import com.malic.muskerrest.dao.userType.UserTypeDao;
import com.malic.muskerrest.entities.User;
import com.malic.muskerrest.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userDao.getUser(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userDao.getUserByUsername(username));
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userDao.addUser(user);
        return ResponseEntity.ok(user);
    }


}
