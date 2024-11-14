## Client Credentials Flow setup with Spring Boot
This Repository shows how to set up a OAuth client for machine-to-machine communication (client credential flow [1]) with Spring boot.

### steps
Done by the people who have the data:
- On the Authorization Server (e.g. Keycloak or bare.id) you need to add a client with Client Credentials Flow [2]. This will give you a client-id and a client-secret.
- The Server that holds the protected data needs to be configured as the Ressource Server with the same Authorization Server configured as the one in the prvious step.

Done by the people who want to access the data:   
- Install org.springframework.boot:spring-boot-starter-oauth2-client
- In your application.yaml you need to update the fields with the data from step 1.   
- add the configurations as they are in the config folder
- call API as described in the MessagesController.java

### more information
I found the following quite helpful:
1) to understand how OIDC and Oauth2 works [3]
2) how to set up OIDC for SPA [4]



[1] https://auth0.com/docs/get-started/authentication-and-authorization-flow/client-credentials-flow)   
[2] https://medium.hexadefence.com/client-credentials-grant-type-with-keycloak-aca4a8b0a708   
[3] https://www.youtube.com/watch?v=996OiexHze0   
[4] https://curity.io/resources/learn/spa-best-practices/   





