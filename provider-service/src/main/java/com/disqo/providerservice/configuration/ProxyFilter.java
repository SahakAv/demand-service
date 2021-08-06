package com.disqo.providerservice.configuration;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class ProxyFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Only allow requests from ZUUL proxy
        HttpServletRequest req = (HttpServletRequest) request;
        String forwardedHeader = req.getHeader("X-Forwarded-From");
        if("ZUUL".equals(forwardedHeader)){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }
}
