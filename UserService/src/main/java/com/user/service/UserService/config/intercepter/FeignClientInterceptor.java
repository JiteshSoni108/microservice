package com.user.service.UserService.config.intercepter;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization",
                "Bearer"+oAuth2AuthorizedClientManager.
                                authorize(OAuth2AuthorizeRequest.
                                        withClientRegistrationId("my-own-client").
                                        principal("internal").
                                        build()).
                                getAccessToken().
                                getTokenValue());
    }
}
