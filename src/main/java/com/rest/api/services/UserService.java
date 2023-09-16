package com.rest.api.services;
import com.rest.api.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    private List<User> userData= new ArrayList<>();
    public UserService() {
        userData.add(new User(UUID.randomUUID().toString(),"Gaurav Aggarwal", "gaurav@gmail.com", "gaurav"));
        userData.add(new User(UUID.randomUUID().toString(),"Saurav Singh", "saurav@gmail.com", "saurav"));
        userData.add(new User(UUID.randomUUID().toString(),"Sachin Gowda", "sachin@gmail.com", "sachin"));
        userData.add(new User(UUID.randomUUID().toString(),"Manish Malhotra", "manish@gmail.com", "manish"));
    }

    public List<User> getUsers() {
        return this.userData;
    }

    public User getUserByEmailId(String emailId) {
        for (User user : userData) {
            if (user.getEmailId().equals(emailId)) {
                return user;
            }
        }
        return null;

    }
}