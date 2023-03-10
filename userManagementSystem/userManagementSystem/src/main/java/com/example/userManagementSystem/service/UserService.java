package com.example.userManagementSystem.service;

import com.example.userManagementSystem.Repository.UserRepo;
import com.example.userManagementSystem.controller.UserController;
import com.example.userManagementSystem.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;
    public  void addUser(User user1) {
         userRepo.save(user1);

    }

    public List<User> getAllUsers() {
       List<User> users=userRepo.findAll();
       return users;
    }

    public List<User> getById(String userId) {
        List<User> user;
        if(null!=userId){
            user=new ArrayList<>();
            user.add(userRepo.findById(Integer.valueOf(userId)).get());
        }else{
            user=userRepo.findAll();
        }
        return user;

    }

    public void deleteUser(String userId) {
            userRepo.deleteById(Integer.valueOf(userId));

    }

    public void updateUser(String user,int userId) {
        JSONObject json=new JSONObject(user);
        User newUser=userRepo.findById(userId).get();
        newUser.setUsername(json.getString("username"));
        newUser.setEmail(json.getString("email"));
        newUser.setDateOfBirth(json.getString("dateOfBirth"));
        String no= json.getString("phoneNumber");
        no="+91"+no;
        newUser.setPhoneNumber(no);
        userRepo.save(newUser);
    }
}
