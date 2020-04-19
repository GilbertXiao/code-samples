package com.gilxyj.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @program: authorization_code
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-20 00:06
 **/
@Configuration
public class AccessTokenConfig {

    @Bean
    TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
