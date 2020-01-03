package com.example.taxtool.filter;

import com.example.taxtool.exception.LimitIpException;
import com.example.taxtool.utils.CommonConstants;
import com.example.taxtool.utils.WebUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author by Lying
 * @Date 2019/11/21
 */
@WebFilter(urlPatterns = "/*")
@Order(Integer.MIN_VALUE)
@Component
@Slf4j
public class RequestFilter implements Filter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String ip = WebUtils.getIP(request);
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RequestInfo.class));
        RequestInfo requestInfo = (RequestInfo) redisTemplate.opsForHash().get(CommonConstants.LIMIT_IP, ip);

        String prefix = "http://www.sanpang.xyz";
        if (!url.startsWith(prefix)) {
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            OutputStream out = response.getOutputStream();
            String result = "滚你妈爬虫";
            out.write(result.getBytes("UTF-8"));
            out.flush();
        }

        System.err.println(url);
        try {
            if (null != requestInfo) {
                if (requestInfo.getRequestNum() > 5) {
                    throw new LimitIpException(ip, url);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.info("ip地址: {}, 请求uri: {}, 请求url: {}", ip, uri, url);
            if (e instanceof RequestRejectedException) {
                if (null == requestInfo) {
                    requestInfo = new RequestInfo(ip, Arrays.asList(buildRemark(ip, url, uri, 1L)), 1L);
                } else {
                    Long requestNum = requestInfo.getRequestNum() + 1;
                    requestInfo.setRequestNum(requestNum);
                    requestInfo.getRemark().add(buildRemark(ip, url, uri, requestNum));
                }
                redisTemplate.opsForHash().put(CommonConstants.LIMIT_IP, ip, requestInfo);
            } else if (e instanceof LimitIpException) {
                response.setContentType("text/html; charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                OutputStream out = response.getOutputStream();
                String result = "<h1 style='color:red'>" +
                        "本网站禁止网络爬虫等恶意程序, 你的网络已被监控, 我方会随时将相关数据提交给网警, 警告:不要面向监狱编程" +
                        "</h1>";

                out.write(result.getBytes("UTF-8"));
                out.flush();
            }
            log.error(e.getLocalizedMessage());
        }

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestInfo implements Serializable {

        private String ip;

        private List<String> remark;

        private Long requestNum;
    }


    private String buildRemark(String ip, String url, String uri, Long requestNum) {
        return String.format("Ip: %s, url: %s, uri: %s, 时间: %s, 请求总数: %s",
                ip, url, uri, LocalDateTime.now(), requestNum);
    }

}
