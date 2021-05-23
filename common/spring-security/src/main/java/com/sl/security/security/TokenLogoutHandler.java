package com.sl.security.security;

import com.sl.util.R;
import com.sl.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shuliangzhao
 * @Title: TokenLogoutHandler
 * @ProjectName acl-parent
 * @Description: TODO
 * @date 2021/5/22 21:10
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
      //从header中获取token
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            tokenManager.removeToken(token);
            String userInfoFromToken = tokenManager.getUserInfoFromToken(token);
            redisTemplate.delete(userInfoFromToken);
        }
        ResponseUtil.out(response, R.ok());
    }
}
