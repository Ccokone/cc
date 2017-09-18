package com.tutorial.gandalf.web;

import com.tutorial.gandalf.Response;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 抽象控制台父类
 *
 * @author Cc
 */
public abstract class AbstractController {

    /**
     * 日志，关键字log4j，用于记录日志；具体的日志配置在resources/common/log4j.properties.
     */
    protected static final Logger WEB_ERROR_LOG = LoggerFactory.getLogger("WEB_ERROR");

    /**
     * JSON转换器，用于一般的序列化转换，过滤掉外部不需要的字端等
     */
    private static final ObjectMapper JSON_MAPPER;

    static {
        JSON_MAPPER = new ObjectMapper();
        Version version = new Version(1, 0, 0, null);
        SimpleModule module = new SimpleModule("GandalfModule", version);
        //序列化入口

        JSON_MAPPER.registerModule(module);
    }

    /**
     * 控制器异常处理方法，当控制器方法执行过程中抛出异常时，将会执行此方法
     *
     * @param ex 异常信息
     * @param request Http 请求
     * @return 错误码为500的响应对象
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex, HttpServletRequest request) throws Exception {
        WEB_ERROR_LOG.error("`Ip`:`" + getRemoteIp(request) + "`, `Url`:`" + request.getRequestURL() + "`, `Params`:`"
                + getParamString(request) + "`", ex);
        Response<Object> response = new Response<>();
        response.setCode(500);
        response.setMessage("阿哦~系统出错了~请稍候重试~");
        return JSON_MAPPER.writeValueAsString(response);
    }

    /**
     * 创建一个code为0的{@link Response}对象,将data设置为{@link Response}中的数据对象。
     * <p>如果json转换过程中出错，将会返回一个code为500的{@link Response}对象，数据对象不会进行设置。
     *
     * @param data
     * @return json
     */
    @SuppressWarnings("unchecked")
    protected String toJsonResponse(Object data, HttpServletRequest request) {
        Response<Object> res;
        if (data instanceof Response) {
            res = (Response<Object>) data;
        } else {
            res = new Response<>();
            res.setData(data);
        }
        try {
            return JSON_MAPPER.writeValueAsString(res);
        } catch (Exception e) {
            WEB_ERROR_LOG.error("Json converter error. original data: " + data, e);
            Response<Object> errorRes = new Response<>();
            errorRes.setCode(500);
            errorRes.setMessage("数据异常，请重试");
            try {
                return JSON_MAPPER.writeValueAsString(errorRes);
            } catch (Exception e1) {
                WEB_ERROR_LOG.error("Check the jackson ObjectMapper configuration.", e1);
                throw new IllegalStateException("Check the jackson ObjectMapper configuration.", e1);
            }
        }
    }

    /**
     * 获取发起请求的客户端IP信息
     *
     * @param request Http 请求
     * @return 客户端IP信息
     */
    protected String getRemoteIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getHeader("X-Cluster-Client-Ip");
        }
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getHeader("X-Forwarded-For");
        }
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    /**
     * 将请求所带的参数转成String用于展现
     *
     * @param request Http请求
     * @return 请求所带的参数转成String
     */
    protected String getParamString(HttpServletRequest request) {
        StringBuilder buffer = new StringBuilder();
        Map<String, String[]> paramMap = request.getParameterMap();
        for (String paramName : paramMap.keySet()) {
            String paramValue = Arrays.toString(paramMap.get(paramName));
            buffer.append(paramName).append("=").append(paramValue).append("&");
        }
        return buffer.toString();
    }
}
