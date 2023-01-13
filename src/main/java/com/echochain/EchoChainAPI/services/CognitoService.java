package com.echochain.EchoChainAPI.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.echochain.EchoChainAPI.configurations.AWSConfig;
import com.echochain.EchoChainAPI.data.DTO.SignUpDTO;
import com.echochain.EchoChainAPI.data.entities.enums.CognitoAttributesEnum;
import com.echochain.EchoChainAPI.exception.FailedAuthenticationException;
import com.echochain.EchoChainAPI.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CognitoService {

    @Autowired
    AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    @Autowired
    AWSConfig awsConfig;

    public Optional<AdminInitiateAuthResult> initiateAuth(String username, String password){
        final Map<String, String> authParams = new HashMap<>();
        authParams.put(CognitoAttributesEnum.USERNAME.name(), username);
        authParams.put(CognitoAttributesEnum.PASSWORD.name(), password);
        authParams.put(CognitoAttributesEnum.SECRET_HASH.name(), calculateSecretHash(awsConfig.getCognito().getAppClientId(), awsConfig.getCognito().getAppClientSecret(), username));

        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withClientId(awsConfig.getCognito().getAppClientId())
                .withUserPoolId(awsConfig.getCognito().getUserPoolId())
                .withAuthParameters(authParams);

        return adminInitiateAuthResult(authRequest);

    }
    public UserType signUp(SignUpDTO signUpDTO){
        try{
            final AdminCreateUserRequest signUpRequest = new AdminCreateUserRequest()
                    .withUserPoolId(awsConfig.getCognito().getUserPoolId())
                    .withTemporaryPassword("Temporary123!")
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
                    .withUsername(signUpDTO.getEmail())
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withUserAttributes(
                            new AttributeType().withName("name").withValue(signUpDTO.getUserName()),
                            new AttributeType().withName("email").withValue(signUpDTO.getEmail()),
                            new AttributeType().withName("phone_number").withValue(signUpDTO.getPhoneNumber()),
                            new AttributeType().withName("phone_number_verified").withValue("true"),
                            new AttributeType().withName("email_verified").withValue("true")

                    );

            AdminCreateUserResult createUserResult = awsCognitoIdentityProvider.adminCreateUser(signUpRequest);
            setUserPassword(signUpDTO.getEmail(), signUpDTO.getPassword());

            return createUserResult.getUser();

        }catch (com.amazonaws.services.cognitoidp.model.UsernameExistsException e) {
            throw new UsernameExistsException("User name that already exists");
        } catch (com.amazonaws.services.cognitoidp.model.InvalidPasswordException e) {
            throw new com.echochain.EchoChainAPI.exception.InvalidPasswordException("Invalid password.", e);
        }
    }

    public AdminSetUserPasswordResult setUserPassword(String username, String password) {

        try {
            // Sets the specified user's password in a user pool as an administrator. Works on any user.
            AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest()
                    .withUsername(username)
                    .withPassword(password)
                    .withUserPoolId(awsConfig.getCognito().getUserPoolId())
                    .withPermanent(true);

            return awsCognitoIdentityProvider.adminSetUserPassword(adminSetUserPasswordRequest);
        } catch (com.amazonaws.services.cognitoidp.model.InvalidPasswordException e) {
            throw new FailedAuthenticationException(String.format("Invalid parameter: %s", e.getErrorMessage()), e);
        }
    }

    private String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new ServiceException("Error while calculating ");
        }
    }

    private Optional<AdminInitiateAuthResult> adminInitiateAuthResult(AdminInitiateAuthRequest request) {
        try {
            return Optional.of(awsCognitoIdentityProvider.adminInitiateAuth(request));
        } catch (NotAuthorizedException e) {
            throw new FailedAuthenticationException(String.format("Authenticate failed: %s", e.getErrorMessage()), e);
        } catch (UserNotFoundException e) {
            String username = request.getAuthParameters().get(CognitoAttributesEnum.USERNAME.name());
            throw new com.echochain.EchoChainAPI.exception.UserNotFoundException(String.format("Username %s  not found.", username), e);
        }
    }
}
