package com.echochain.EchoChainAPI.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.echochain.EchoChainAPI.configurations.AWSConfig;
@Configuration
public class AppConfig {

    @Autowired
    AWSConfig awsConfig;

    @Bean
    public AWSCognitoIdentityProvider awsCognitoIdentityProvider() {
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider
                        (new BasicAWSCredentials(
                                awsConfig.getAccesskey(), awsConfig.getSecretKey()
                ))).withRegion(awsConfig.getRegion()).build();
    }
}
