package com.grokonez.jwtauthentication.service;

import com.grokonez.jwtauthentication.message.response.GeneralResponseObject;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public GeneralResponseObject getProfile(String userName){
        GeneralResponseObject generalResponseObject = new GeneralResponseObject();
        try {
            User user = userRepository.findByUsername(userName);
            generalResponseObject.setData(user);
            generalResponseObject.setMessage("done");
            generalResponseObject.setStatus(true);
        }catch (Exception e){
            generalResponseObject.setMessage(e.getMessage());
        }
        return generalResponseObject;
    }
}
