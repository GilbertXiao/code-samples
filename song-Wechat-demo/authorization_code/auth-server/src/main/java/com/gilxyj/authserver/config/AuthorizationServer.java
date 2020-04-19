package com.gilxyj.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @program: authorization_code
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-20 00:10
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Bean
    AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setAccessTokenValiditySeconds(60*60*2);
        tokenServices.setRefreshTokenValiditySeconds(60*60*24*3);
        return tokenServices;
    }

    /**
     * AuthorizationServerSecurityConfigurer 用来配置令牌端点的安全约束，也就是这个端点谁能访问，谁不能访问。checkTokenAccess 是指一个 Token 校验的端点，这个端点我们设置为可以直接访问（在后面，当资源服务器收到 Token 之后，需要去校验 Token 的合法性，就会访问这个端点）
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * ClientDetailsServiceConfigurer 用来配置客户端的详细信息，授权服务器要做两方面的检验，一方面是校验客户端，另一方面则是校验用户，校验用户，我们前面已经配置了，这里就是配置校验客户端。客户端的信息我们可以存在数据库中，这其实也是比较容易的，和用户信息存到数据库中类似，但是这里为了简化代码，我还是将客户端信息存在内存中，这里我们分别配置了客户端的 id，secret、资源 id、授权类型、授权范围以及重定向 uri。授权类型我在上篇文章中和大家一共讲了四种，四种之中不包含 refresh_token 这种类型，但是在实际操作中，refresh_token 也被算作一种。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("song")
                .secret(new BCryptPasswordEncoder().encode("123"))
                .resourceIds("res1")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all")
                .redirectUris("http://localhost:8082/index.html");
    }


    /**
     * AuthorizationServerEndpointsConfigurer 这里用来配置令牌的访问端点和令牌服务。authorizationCodeServices用来配置授权码的存储，这里我们是存在在内存中，tokenServices 用来配置令牌的存储，即 access_token 的存储位置，这里我们也先存储在内存中。有小伙伴会问，授权码和令牌有什么区别？授权码是用来获取令牌的，使用一次就失效，令牌则是用来获取资源的，如果搞不清楚，建议重新阅读上篇文章恶补一下：做微服务绕不过的 OAuth2，松哥也来和大家扯一扯
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authorizationCodeServices(authorizationCodeServices())
                .tokenServices(tokenServices());

    }

    @Bean
    AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }
}
