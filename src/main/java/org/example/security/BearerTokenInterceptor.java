package org.example.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import org.springframework.web.servlet.HandlerInterceptor;

public class BearerTokenInterceptor implements HandlerInterceptor{
    private BearerToken token;

    public BearerTokenInterceptor(BearerToken token) {
        this.token = token;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            String auth_token = authorization.substring(7, authorization.length());

            if (this.token.getToken() == null || !this.token.equals(token.getToken())) {
                this.token.setToken(auth_token);
            }
        }

        return true;
    }
}
