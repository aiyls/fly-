package cn.aiyls.fly.utils;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.mapper.UserMapper;
import cn.aiyls.fly.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisUtil redisUtil;
    private final JwtUtils jwtUtils;

    private final UserMapper userMapper;

    @Autowired
    public JwtAuthenticationTokenFilter( JwtUtils jwtUtils, RedisUtil redisUtil,UserMapper userMapper) {
        this.jwtUtils = jwtUtils;
        this.redisUtil = redisUtil;
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains(".") || uri.contains("login")) {
            filterChain.doFilter(request,response);
            return;
        }

        if (jwtUtils != null) {
            if (!uri.contains("login")) {
                String token = request.getHeader("authorization");
                String version = request.getParameter("version");
                String errorMsg = null;
                if (token == null || token.isEmpty()) {
                    errorMsg = "用户token不能为空";
                } else {
                    String account = redisUtil.getValue(token, Constant.TOKEN).toString();
                    User user =
                    if (account.isEmpty()) {
                        errorMsg = "用户信息不存在";
                    }

                }
                response
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
