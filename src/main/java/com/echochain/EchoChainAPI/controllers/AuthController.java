package com.echochain.EchoChainAPI.controllers;

import com.amazonaws.services.cognitoidp.model.UserType;
import com.echochain.EchoChainAPI.response.BaseResponse;
import com.echochain.EchoChainAPI.data.DTO.LoginDTO;
import com.echochain.EchoChainAPI.data.DTO.SignUpDTO;
import com.echochain.EchoChainAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse> signUp(@RequestBody @Validated SignUpDTO signUpDTO){
        UserType result = userService.createUser(signUpDTO);
        return new ResponseEntity<>(new BaseResponse(
                result, "User account created successfully", false), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody @Validated LoginDTO loginRequest){
        return new ResponseEntity<>(userService.authenticate(loginRequest), HttpStatus.OK);
    }
}
