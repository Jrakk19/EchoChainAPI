package com.echochain.EchoChainAPI.services;

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.echochain.EchoChainAPI.Responses.AuthenticatedResponse;
import com.echochain.EchoChainAPI.Responses.BaseResponse;
import com.echochain.EchoChainAPI.data.DTO.AuthenticatedChallengeDTO;
import com.echochain.EchoChainAPI.data.DTO.LoginDTO;
import com.echochain.EchoChainAPI.data.DTO.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.amazonaws.services.cognitoidp.model.ChallengeNameType.NEW_PASSWORD_REQUIRED;

@Service
public class UserService {

    @Autowired
    CognitoService cognitoService;
    public UserType createUser(SignUpDTO signUpDTO){
        return cognitoService.signUp(signUpDTO);
    }

    public BaseResponse authenticate(LoginDTO userLogin){
        AdminInitiateAuthResult result = cognitoService.initiateAuth(userLogin.getUsername(), userLogin.getPassword())
                .orElseThrow(() -> new UserNotFoundException(String.format("Username %s not found.", userLogin.getUsername())));

        if(ObjectUtils.nullSafeEquals(NEW_PASSWORD_REQUIRED.name(), result.getChallengeName())){
            return new BaseResponse(new AuthenticatedChallengeDTO(
                    NEW_PASSWORD_REQUIRED.name(),
                    result.getSession(),
                    userLogin.getUsername()),
                    "First time login - Password change required", false
                    );
        }
        return new BaseResponse(new AuthenticatedResponse(
                result.getAuthenticationResult().getAccessToken(),
                result.getAuthenticationResult().getIdToken(),
                result.getAuthenticationResult().getRefreshToken(),
                userLogin.getUsername()), "Login Successful", false);
    }
}
