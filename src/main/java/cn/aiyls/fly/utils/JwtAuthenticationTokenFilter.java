package cn.aiyls.fly.utils;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.UserMapper;
import cn.aiyls.fly.redis.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        if (uri.contains(".") || uri.contains("login") || uri.contains("register")
                || uri.contains("region")
                || uri.contains("dynamicList")
                || uri.contains("dynamicDetail")) {
            filterChain.doFilter(request,response);
            return;
        }

        if (jwtUtils != null) {
            String token = request.getHeader("authorization");
            String version = request.getParameter("version");
            Result resultEntity = new Result();
            if (token == null || token.isEmpty()) {
                resultEntity.setMessage("用户token不能为空");
            } else {
                String account = redisUtil.getValue(token, Constant.TOKEN).toString();
                User user = JwtUtils.getUser(request);
                if (account.isEmpty() || user.getUserName().isEmpty()) {
                    resultEntity.setMessage("用户信息不存在");
                }
            }
            if (resultEntity.getMessage() != null) {
                resultEntity.setCode(201);
                String gson = JSONObject.toJSONString(resultEntity);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.append(gson);
                return ;
            }
        }
        filterChain.doFilter(request,response);
    }
}
