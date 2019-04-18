package com.grokonez.jwtauthentication.service;

import com.grokonez.jwtauthentication.message.response.GeneralResponseObject;
import com.grokonez.jwtauthentication.message.response.ResponseObject;
import com.grokonez.jwtauthentication.model.Request;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.RequestRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RequestRepository requestRepository;
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
    public GeneralResponseObject getAllRequestsToApprove(long id){
        GeneralResponseObject generalResponseObject = new GeneralResponseObject();
        try{
            User user = userRepository.getOne(id);
            generalResponseObject.setData(user.getShouldApprove());
            generalResponseObject.setMessage("done");
            generalResponseObject.setStatus(true);
        }catch (Exception e){
            generalResponseObject.setMessage(e.getMessage());
        }
        return generalResponseObject;
    }
    public ResponseObject attachTheRequestToManager(int requestId){
        ResponseObject responseObject = new ResponseObject();
        try{
            Request request = requestRepository.getOne(requestId);
            User manager = request.getShouldApprove().getManager();
            if(manager == null){
                request.setStatus("Accepted");
            }else{
                request.setShouldApprove(manager);
            }
            requestRepository.save(request);
            responseObject.setStatus(true);
            responseObject.setMessage("done");
        }catch(Exception e){
            responseObject.setMessage(e.getMessage());
        }
        return responseObject;

    }
    public GeneralResponseObject getAllUsers(){
        GeneralResponseObject generalResponseObject = new GeneralResponseObject();
        try {
            generalResponseObject.setData(userRepository.findAll());
            generalResponseObject.setStatus(true);
            generalResponseObject.setMessage("done");
        }catch (Exception e){
            generalResponseObject.setMessage(e.getMessage());
        }
        return generalResponseObject;
    }
}
