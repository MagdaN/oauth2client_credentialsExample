package com.example.auth.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;


@RestController
public class MessagesController {

  private final OAuth2AuthorizedClientManager authorizedClientManager;

  @Autowired
  public MessagesController(OAuth2AuthorizedClientManager authorizedClientManager) {
    this.authorizedClientManager = authorizedClientManager;
  }

  @GetMapping("/test")
  public String callProtectedApi() {
    OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("<name of the client registration>")
            .principal("client_credentials")  // Any non-null value (required but arbitrary for client_credentials)
            .build();

    OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);

    if (authorizedClient == null) {
      throw new IllegalStateException("Client authorization failed");
    }

    // Get the access token
    String accessToken = authorizedClient.getAccessToken().getTokenValue();

    // Step 2: Use the token to call the protected API
    RestClient.Builder restClientBuilder = RestClient.builder();
    RestClient restClient = restClientBuilder
            .defaultHeaders(headers -> headers.setBearerAuth(accessToken))
            .baseUrl("<the url that holds the protected API>")
            .build();

    String apiResponse = restClient.get().uri("").retrieve().body(String.class);

    return apiResponse;

  }

}
