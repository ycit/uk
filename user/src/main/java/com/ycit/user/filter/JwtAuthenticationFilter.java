package com.ycit.user.filter;

import com.ycit.common.util.JwtUtil;
import com.ycit.user.util.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求拦截 jwt 校验
 *
 * @author uk
 * 2019/3/31 17:03
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(Constants.HEADER_TOKEN);
        String username = null;
        if (StringUtils.isNotBlank(token)) {
            try {
                username = JwtUtil.getUsernameByToken(token);
            } catch (IllegalArgumentException e) {
                log.error("the token is null or empty or whitespace, exception is {}", e);
            } catch (ExpiredJwtException e) {
                log.error("the token is expired, exception is {}", e);
            } catch (SignatureException e){
                log.error("the token validation fails, exception is {}", e);
            } catch (MalformedJwtException e) {
                log.error("the token is not a valid token, exception is {}", e);
            } catch (UnsupportedJwtException e) {
                log.error("the token argument does not represent an Claims JWS, exception is {}", e);
            }
        } else {
            log.warn("could not find bearer string, ");
        }
        if (StringUtils.isNotBlank(username)) {

        }
        filterChain.doFilter(request, response);
    }
}
