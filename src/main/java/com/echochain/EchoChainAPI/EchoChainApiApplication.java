package com.echochain.EchoChainAPI;

import com.echochain.EchoChainAPI.configurations.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EchoChainApiApplication {


	public static void main(String[] args) {

		SpringApplication.run(EchoChainApiApplication.class, args);
	}


}
