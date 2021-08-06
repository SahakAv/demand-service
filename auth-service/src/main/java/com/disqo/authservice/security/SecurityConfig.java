package com.disqo.authservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.disqo.authservice.client.response.UserType.CUSTOMER;
import static com.disqo.authservice.client.response.UserType.SERVICE_PROVIDER;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login-service/auth", "/login-service/signup").permitAll()
                //Provider creation should be allowed only users with service provider auth
                .antMatchers(HttpMethod.POST, "/provider-service/provider").hasAuthority(SERVICE_PROVIDER.name())
                .antMatchers(HttpMethod.GET, "/provider-service/provider/request/*").hasAuthority(SERVICE_PROVIDER.name())
                .antMatchers(HttpMethod.POST, "/customer-service/customer/request").hasAuthority(CUSTOMER.name())
                .anyRequest().authenticated().and().apply(new JwtConfigurer(jwtTokenProvider));

    }
}
