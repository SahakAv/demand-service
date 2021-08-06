package com.disqo.authservice.configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RedirectionFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //To pass user after redirects to microservices
        if(ctx.getRequest().getHeader("authorization" ) != null){
            String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            ctx.getZuulRequestHeaders().put("username", username);
        }
        return null;
    }
}
