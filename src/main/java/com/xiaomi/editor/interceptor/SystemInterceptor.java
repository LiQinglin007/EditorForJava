package com.xiaomi.editor.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

/**
 * 记录参数拦截器
 */
public class SystemInterceptor implements HandlerInterceptor {

    private String[] allowUrls;// 不过滤地址
    private String charset; // 字符集

    public String getCharset() {
        return charset == null ? "utf-8" : charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    Logger logger = Logger.getLogger(SystemInterceptor.class);


    /**
     * 拦截在处理之前   比如检查token是否过期等信息
     * 预处理回调方法，实现处理器的预处理（如登录检查）
     *
     * @param request
     * @param response
     * @param handler  第三个参数为响应的处理器，就是哪个Controller响应这个请求；
     * @return true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setHeader("Content-type", "textml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getServletPath();
        String header = request.getHeader(FinalData.TOKENHEAD);

//        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
//        StringBuilder responseStrBuilder = new StringBuilder();
//        String inputStr;
//        while ((inputStr = streamReader.readLine()) != null)
//            responseStrBuilder.append(inputStr);
//
//        JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
//        String param = jsonObject.toJSONString();
//        System.out.println("aaaa:" + responseStrBuilder.toString());


        // 过滤静态文件
        if (url.indexOf("/static/") > -1) {
            return true;
        }
        // 过滤首页
        if ("/".equals(url)) {
            return true;
        }

        String s = "";
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            s = s + name + ":" + value + ",";
        }
        logger.warn("接口：" + url + "入参：" + s);

        //通过
        if (url != null && checkUrls(url)) {
            return true;
        }

        //检查token
        if (CheckStringEmptyUtils.IsEmpty(header)) {
            ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("token不能为空");
            System.out.println(url + "被拦截了,token不能为空");
            response.getWriter().write(JSONObject.toJSONString(responseJSON));
            return false;
        }
        String tokenvalue = "";
        if (url.contains("upload")) {//文件上传的
            tokenvalue = JedisClientUtil.getString(FinalData.SYSTEM_TOKEN + header);
            if (CheckStringEmptyUtils.IsEmpty(tokenvalue)) {
                tokenvalue = JedisClientUtil.getString(FinalData.APP_TOKEN + header);
            }
        }

        if (url.contains(FinalData.SYSTEM_BASEURL)) {//验证token(系统用户的token)
            tokenvalue = JedisClientUtil.getString(FinalData.SYSTEM_TOKEN + header);
        } else if (url.contains(FinalData.APP_BASEURL)) {//App客户端用户
            tokenvalue = JedisClientUtil.getString(FinalData.APP_TOKEN + header);
        }

        if (CheckStringEmptyUtils.IsEmpty(tokenvalue)) {
            ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("token失效,请重新登录");
            System.out.println(url + "被拦截了,token失效");
            response.getWriter().write(JSONObject.toJSONString(responseJSON));
            return false;
        }

        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {

    }


    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，
     * 类似于try-catch-finally中的finally，但仅调用处理器执行链中
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    /**
     * 检查url
     *
     * @param url
     * @return
     * @author: LSW
     * @Createtime: 2018年5月18日
     */
    private boolean checkUrls(String url) {
        boolean result = false;
        if (allowUrls == null) {
            return true;
        }
        for (String uri : allowUrls) {
            if (url != null && url.indexOf(uri) > -1) {
                result = true;
                break;
            }
        }
        return result;
    }

    public String[] getAllowUrls() {
        return allowUrls;
    }

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }
}
