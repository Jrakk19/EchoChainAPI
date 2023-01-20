package com.echochain.EchoChainAPI.configurations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("aws")
public class AWSConfig {

    private String accesskey;
    private String secretKey;
    private String region;
    private final Cognito cognito = new Cognito();

    private final S3 s3 = new S3();

    public S3 getS3() {
        return s3;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Cognito getCognito() {
        return cognito;
    }

    public String getAccesskey(){
        return accesskey;
    }

    public void setAccesskey(String accesskey){
        this.accesskey = accesskey;
    }
    @Override
    public String toString(){
        return "{" + "\naccessKey: " + this.getAccesskey() + "\nsecretKey: " + this.getSecretKey() + "\nregion: " + this.getRegion() + "\ncognito: " + this.getCognito() +  "}";
    }


    public static class S3{
        public String bucketName;

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }
    }
    public static class Cognito {
        public String getUserPoolId() {
            return userPoolId;
        }

        public void setUserPoolId(String userPoolId) {
            this.userPoolId = userPoolId;
        }

        public String getAppClientId() {
            return appClientId;
        }

        public void setAppClientId(String appClientId) {
            this.appClientId = appClientId;
        }

        public String getAppClientSecret() {
            return appClientSecret;
        }

        public void setAppClientSecret(String appClientSecret) {
            this.appClientSecret = appClientSecret;
        }

        private String userPoolId;
        private String appClientId;
        private String appClientSecret;
        @Override
        public String toString(){
            return " {" + "\nuserPoolId: " + this.getUserPoolId() + "\nappClientId: " + this.getAppClientId() + "\nappClientSecret: " + this.getAppClientSecret() + "}";
        }
    }
}
