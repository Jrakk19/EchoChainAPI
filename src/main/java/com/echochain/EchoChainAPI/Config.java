package com.echochain.EchoChainAPI;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("aws")
public class Config {

    private String accesskey;
    public String getAccesskey(){
        return accesskey;
    }

    public void setAccesskey(String accesskey){
        this.accesskey = accesskey;
    }
    @Override
    public String toString(){
        return "{" + this.getAccesskey() + "}";
    }



}
