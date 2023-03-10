package com.example.userManagementSystem.Util;

import com.example.userManagementSystem.model.User;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    public static User setUser(JSONObject json) {

        User user=new User();
        user.setUsername(json.getString("username"));
        user.setDateOfBirth(json.getString("dateOfBirth"));
        user.setEmail(json.getString("email"));

        String no=json.getString("phoneNumber");
        no="+91"+no;
        user.setPhoneNumber(no);
        LocalTime currentTime=LocalTime.now();
        user.setTime(currentTime);
        LocalDate currentDate=LocalDate.now();
        user.setDate(currentDate);
        return user;
    }

    public static List<String> validate(JSONObject json) {
        List<String> errorList= new ArrayList<>();
        if(!json.has("username")){
            errorList.add("username");
        }
        if(!json.has("dateOfBirth")){
            errorList.add("dateOfBirth");
        }
        else{
            String dob=json.getString("dateOfBirth");
            System.out.println(dob);
            boolean ans=Validation.isValidDate(dob);
            if(ans==false){
                errorList.add("Enter valid Date of Birth. Date should be in dd-mm-yyyy");
            }
        }
        if(!json.has("phoneNumber")){
            errorList.add("phoneNumber");
        }else{
            String number=json.getString("phoneNumber");
            boolean ans=Validation.isValidPhoneNumber(number);
            if(ans==false){
                errorList.add("Enter Valid Phone Number");
            }
        }if(!json.has("email")){
            errorList.add("email");
        }else{
            String email=json.getString("email");
            boolean ans=Validation.isValidEmail(email);
            if(ans==false){
                errorList.add("Enter a valid email address");
            }
        }
        return errorList;
    }
}
