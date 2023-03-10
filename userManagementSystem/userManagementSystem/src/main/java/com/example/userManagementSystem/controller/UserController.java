package com.example.userManagementSystem.controller;

import com.example.userManagementSystem.Util.UserUtil;
import com.example.userManagementSystem.model.User;
import com.example.userManagementSystem.service.UserService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(value = "/addUser")
    public ResponseEntity<String> addUser(@RequestBody String user){
        JSONObject json= new JSONObject(user);
        List<String> validationList= UserUtil.validate(json);
        if(validationList.isEmpty()){
            User user1=UserUtil.setUser(json);
           userService.addUser(user1);
            return new ResponseEntity<String>("user saved", HttpStatus.CREATED);
        }else{
            String[] errorList= Arrays.copyOf(validationList.toArray(),validationList.size(),String[].class);
            return new ResponseEntity<String>("Please pass these mandatory parameters-"+Arrays.toString(errorList),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/getById/{userId}")
    public List<User> getById(@Nullable@RequestParam String userId){
        return userService.getById(userId);
    }
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<String>("user deleted-"+userId, HttpStatus.OK);
    }
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody String user,@PathVariable int userId){
        userService.updateUser(user,userId);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

}
