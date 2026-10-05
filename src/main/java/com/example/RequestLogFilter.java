package com.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class RequestLogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Запрос: " + LocalDateTime.now());
        chain.doFilter(req, resp);
    }
}