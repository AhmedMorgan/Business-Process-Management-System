package com.grokonez.jwtauthentication.service;

import com.grokonez.jwtauthentication.message.response.ResponseObject;
import com.grokonez.jwtauthentication.model.Request;
import com.grokonez.jwtauthentication.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;
    public ResponseObject createRequest(Request request){
        ResponseObject responseObject = new ResponseObject();
        try{
            requestRepository.save(request);
            responseObject.setMessage("request created successfully");
            responseObject.setStatus(true);
        }catch (Exception e) {
            responseObject.setMessage(e.getMessage());
        }
        return responseObject;
    }
    public ResponseObject refuseRequest(int id){
        ResponseObject responseObject = new ResponseObject();
        try{
            Request request = requestRepository.getOne(id);
            request.setStatus("Rejected");
            request.setShouldApprove(null);
            responseObject.setMessage("done");
            responseObject.setStatus(true);
            requestRepository.save(request);
        }catch (Exception e){
            responseObject.setMessage(e.getMessage());
        }
        return responseObject;
    }
}
