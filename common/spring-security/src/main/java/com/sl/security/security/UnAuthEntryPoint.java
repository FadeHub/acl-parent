package com.sl.security.security;

import com.sl.util.R;
import com.sl.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shuliangzhao
 * @Title: UnAuthEntryPoint
 * @ProjectName acl-parent
 * @Description: TODO
 * @date 2021/5/22 21:17
 */
public class UnAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(httpServletResponse, R.error());
    }
}
