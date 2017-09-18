package com.tutorial.gandalf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试控制器
 * <p>
 *     {@code @Controller} 控制器都必须设置的注解，暂时死记
 *     {@code @RequestMapping} 相当于接口URI的路径设定。可用于类，函数上，好比外部连接 http://{host}:{port}/{demo}/{method}.htm
 *     {@code @ResponseBody} 用户标识返回内容为JSON结构，如果没有此标识，则返回的是VM地址，也就是页面
 * </p>
 *
 * @author Cc
 */

@Controller
@RequestMapping(value = "/demo", produces = "text/plain;charset=UTF-8")
public class DemoController extends AbstractController {

    /* 返回JSON格式1 访问连接： http://localhost:8080/gandalf/demo/say-hello.htm */
    @RequestMapping(value = "/say-hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(HttpServletRequest request) {
        return toJsonResponse("Hello, lady! Welcome to Web", request);
    }

    /* 返回JSON格式2 访问连接：http://localhost:8080/gandalf/demo/say-hellos.htm */
    @RequestMapping(value = "/say-hellos", method = RequestMethod.GET)
    @ResponseBody
    public String sayHellos(HttpServletRequest request) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> english = new HashMap<>();
        english.put("Language", "English");
        english.put("Words", "Hello, lady! Welcome to Web Services");
        list.add(english);

        Map<String, Object> chinese = new HashMap<>();
        chinese.put("Language", "Chinese(Simple)");
        chinese.put("Words", "女士你好，欢迎来到 Web 服务！");
        list.add(chinese);


        Map<String, Object> spanish = new HashMap<>();
        spanish.put("Language", "Spanish");
        spanish.put("Words", "Hola damas, bienvenido al servicio web!");
        list.add(spanish);

        Map<String, Object> japanese = new HashMap<>();
        japanese.put("Language", "Japanese");
        japanese.put("Words", "こんにちは女性、ウェブサービスへようこそ！");
        list.add(japanese);

        Map<String, Object> chinese1 = new HashMap<>();
        chinese1.put("Language", "Chinese(Traditional)");
        chinese1.put("Words", "女士你好，歡迎來到 Web 服務！");
        list.add(chinese1);
        return toJsonResponse(list, request);
    }

    /* 返回页面的形式。页面的路径从 webapp/WEB-INFO/velocity 开始，访问连接 http://localhost:8080/gandalf/demo/say-hello-with-page.htm */
    @RequestMapping(value = "/say-hello-with-page", method = RequestMethod.GET)
    public String sayHello2(HttpServletRequest request) {
        return "welcome";
    }
}
