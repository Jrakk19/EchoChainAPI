# EchoChain 

## Introduction:

>    EchoChain is a multiplayer mobile party game in a style similar to Jackbox
    games. Most party games require the players to be in the same room however 
    in situations such as the pandemic or where friends are not in the state there
    is no substitution for this issue. EchoChain fixes this problem by turning the
    party game entirely into a mobile-based game where players could be in a Skype call, discord channel, or any other online meeting platform and be able to
    play this game. The game is based on a classic game of telephone where each
    player is given a prompt, players will record their voice trying to mimic the prompt, and that audio is sent to the next player who must guess what the
    prompt was for that audio recording. At the end of the game, it will show
    each player's guess and recording in order to show how the chain evolved or
    changed throughout the game.  

 

## High-Level Functional and Nonfunctional Requirements: 

#### High-Level Functional Requirements: 

- Users and Create or Join a Game 

- Users can create a prompt to give to another player 

- Users can record themselves and Submit them to the game 

- Users can make a guess as to what the audio was mimicking 

- Users can view the chains at the end of the game 

- Users can exit the game at the end 

#### Nonfunctional Requirements:

- System can scale depending on the number of requests 

- System will clear unused data for data integrity purposes 

 

## Software and Cloud Technologies:

#### Software Technologies: 

	- Spring 

>	This was used as my API Framework because of its strict and synchronous rules   as well as its ease of integration with docker and other Third Party Services 

	- JDBC 

>	This was used to make queries to my database from my API because of its ease of integration as well as its annotation style of writing Entities 

	- Docker 

>	This was used to package and deploy my application because it can be deployed on most cloud hosting platforms and most virtual machines with ease 

	- Pusher Channels 

>	This was used to keep my game real-time and communicate with all of my client-sided software when to perform certain actions. 

	- Mockito 

>	This was used for my unit tests as it is easy to use and comes with many great tools to make writing unit tests quick and efficient 

	- Maven 

>	This was used to package my application in order to build a docker image for it. It was also used to manage my dependencies. 

	- PostgreSQL 

>	I chose this for my database as I had a lot of relational data and it is easily hosted and connected to my API. 

	- React Native 

>	This was used for my front-end framework because it can run on both Android and IOS and can be written in React Style JavaScript making development easy as well as debugging and access to npm modules easier in the long run. 

	- TestFlight 

>	This was used to deploy and beta test my Mobile application as you can deploy and have people test without putting the app on the Appstore which made it perfect for this project. 

	- CocoaPods 

>	This was used to manage my IOS dependencies such as access to the phone's Mic or npm modules that needed access to the internal workings of the phone. 

	- Prettier 

>	This was used to keep my code formatted so that it would not become a mess throughout the project. 

 

## Cloud Technologies: 

	- AWS Cognito 

>	This was used to manage my JWT tokens and manage users as a way to secure my API 

	- AWS S3 Buckets 

>	This was used as file storage for my audio recordings to not put strain on my database 

	- AWS Fargate 

>	This was used to host my Docker Image to a virtual machine on AWS 

	- AWS CloudWatch 

>	This was used to view the logs of my application which can make debugging production much easier rather than guessing what is wrong.  

	- AWS ECR 

>	This was used as a repository for my docker images or releases. 

	 

## DevOps Principles: 

- I made sure I had a service to view production logs in order to debug and manage my application health.  

- I had a separate user on AWS to make sure my admin-level access keys were not exposed to the public viewing 

	 

## New Technologies:

#### Technologies Learned during the course of the project were: 

	- TestFlight 

>	The reason I chose this Technology was because I needed a way to deploy and beta test my mobile application without deploying to the AppStore which made this perfect for this project 

	- Pusher Channels 

>	The reason I chose this Technology was I needed some form of WebSocket or messaging library that could send a message to multiple clients at the same time in order to keep my game real-time. This made this technology perfect as it could send a message from my API to all clients subscribed to its channel at the same time. 

	- AWS S3 Buckets 

>	The reason I chose this Technology was because I needed to store audio files somewhere as I would need to grab them later for the game so I used Amazon S3 Buckets in order to store these files and then when needed it could retrieve them from the bucket. 

	- AWS Cognito 

>	The reason I chose this Technology was because I needed a way to secure my API using JWT tokens but didn’t want to manage that software myself so I looked for a kind of OAuth provider and chose Cognito as it would also manage users' information and not just use an email to create an account.  

	- Mockito 

>	The reason I chose this technology was because it is a widely used library for unit testing in Spring and looked easy to integrate and use which it was.  

 

## Technical Approach: 

>	I used a react tree like structure for my Project structure in order to keep everything organized. I also made a component out of anything that could be made into a component which made everything Easier to debug.  

>	On my loading page I used Pusher in order to listen for an event message that would tell the game where to go. This can be seen in the diagram below 

 

>	The flow of the game is shown in the following diagram. 

	 
 

>	In my API I follow a Controller -> Service -> Repository like structure. Controllers would handle the requests while the service handles most of if not all of the logic and the repository handles all database queries. A diagram of my API and the previously mentioned flow can be seen below. 

	 
 

>	The chain functionality is a combination of most of the functionality in my application and the retrieval of a full chain object was the biggest area in the project when it comes to a single entity being retrieved from the Back-End. The process for this can be seen below.  

	 

 

## Risks and Challenges:

#### Risks: 

>	- One Risk was that I had never used Audio Recording software like this before and didn’t know exactly how it would work on react native which could have broken my entire project 

>	- This project was pretty extensive when it came to making everything line up so one risk was getting confused on my own project which would have made it very hard to continue development 

>	- A lot of the data was relational so it could have been very easy for data to get very messed up during testing etc. 

>	- I was able to overcome most of these by doing proof of concept applications and doing a large amount of research to make sure what I was doing was possible and could be done within my system. 

#### Challenges: 

>	- My biggest challenge was getting the audio recording to work on the phone and then sending that recording to my S3 bucket. A lot of issues came up when developing this but I was able to get past it by looking through GitHub issues and also researching npm modules that could help me.  

>	- Another challenge was getting real-time to work the way I wanted it to as I only wanted to send a pusher message under specific circumstances. 

>	- I also came across an issue when I needed to get all the chain data and display it to the front end in a staggered or delayed way which means I needed to render components one after the other with a delay in between. I was able to do so by using JavaScripts setTimeout function and Reacts behavior with rendering state 

>	- The last issue that I had was with AWS as I tried one of their deployment software and it ended up charging me a very large amount of money and when trying to resolve this they said not to use their system while it was being investigated so at the very end of the project I had to switch the API hosting platform to Heroku  

 

## Outstanding Issue: 

 >Currently the only issues would be some data validation such as checking if the user recorded an audio clip or if the guess was empty etc. Other than that there are no known outstanding issues. 

 
