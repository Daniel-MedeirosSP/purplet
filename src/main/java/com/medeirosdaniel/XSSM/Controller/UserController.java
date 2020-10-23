package com.medeirosdaniel.XSSM.Controller;

import com.medeirosdaniel.XSSM.Entity.UserEntity;
import com.medeirosdaniel.XSSM.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @GetMapping(value="/id/{id}")
    public Optional<UserEntity> findById(@PathVariable("id") String id){
        return userService.findById(id);
    }

    @PostMapping
    public UserEntity createOrUpdate(@RequestBody UserEntity userEntity) throws Exception {
    return userService.crateOrUpdate(userEntity);
    }

    @GetMapping(value="/unlock/{code}/{username}")
    public Boolean unlockAccount(@PathVariable("code") String code, @PathVariable("username") String username){
        return userService.lockAccount(username,code);
    }
}
