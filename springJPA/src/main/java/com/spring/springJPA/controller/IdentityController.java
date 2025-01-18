package com.spring.springJPA.controller;

import com.spring.springJPA.entity.Identity;
import com.spring.springJPA.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/identity")
public class IdentityController {

    @Autowired
    IdentityService identityService;

    @GetMapping("/getIdentities")
    public ResponseEntity<?> getIdentities(){
        return new ResponseEntity<>(identityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getIdentity/{id}")
    public ResponseEntity<?> getIdentity(@PathVariable("id") int identityId){
        Optional<Identity> identity = identityService.getById(identityId);
        if(identity.isPresent())
            return new ResponseEntity<>(identity, HttpStatus.FOUND);
        else
            return new ResponseEntity<>("Identity not found",HttpStatus.NOT_FOUND);
    }


}
