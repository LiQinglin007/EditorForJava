package com.xiaomi.editor.controller;

import com.xiaomi.editor.bean.UserBean;
import com.xiaomi.editor.paramsbean.ForgotPasswordBean;
import com.xiaomi.editor.paramsbean.LoginBean;
import com.xiaomi.editor.paramsbean.RegisterBean;
import com.xiaomi.editor.service.IUserBeanService;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 用户模块<br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-06-21<br>
 * Time: 9:05<br>
 * UpdateDescription：<br>
 */

@Controller
@RequestMapping("api/user")

public class UserController {

    @Resource
    private IUserBeanService mUserBeanService;


    @ResponseBody
    @RequestMapping("/register")
    public ResponseJSON register(@RequestBody RegisterBean registerBean
    ) {
        ResponseJSON responseBean = ResponseUtils.getFiledResponseBean("注册失败", null);
        //检查字段是否为空
        String checkStringList = CheckStringEmptyUtils.CheckStringList(
                new CheckStringEmptyUtils.CheckStringBean(registerBean.getPhone(), "手机号码不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(registerBean.getPassword(), "密码不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(registerBean.getMessageCode(), "验证码不能为空"));
        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseBean.setMsg(checkStringList);
            return responseBean;
        }
        //检查验证码是否正确及过期
        if (!CheckMessageCodeUtil.checkCode(CheckMessageCodeUtil.MESSAGE_REGISTER, registerBean.getPhone(), registerBean.getMessageCode())) {
            responseBean.setMsg("验证码不正确");
            return responseBean;
        }
        //检查有没有被注册过
        UserBean userBeans = mUserBeanService.selectByPhone(registerBean.getPhone());
        if (userBeans != null) {
            responseBean.setMsg("该手机号已注册，请登录");
            return responseBean;
        }
        //插入
        UserBean userBean = new UserBean("用户" + RandomUtils.getRandom(6, RandomUtils.NUMBER),
                MD5Util.string2MD5(registerBean.getPassword()), (float) 0, registerBean.getPhone());
        int insert = mUserBeanService.insert(userBean);
        if (insert == 0) {
            responseBean.setMsg("注册失败");
            return responseBean;
        }
        responseBean = ResponseUtils.getSuccessResponseBean("注册成功", null);
        return responseBean;
    }


    /**
     * 用户登录
     *
     * @param loginBean 用户名,密码,用户类型
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON login(@RequestBody LoginBean loginBean) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("登录失败", null);
//        //检查参数
        String checkStringList = CheckStringEmptyUtils.CheckStringList(
                new CheckStringEmptyUtils.CheckStringBean(loginBean.getPhoneNumber(), "用户名不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(loginBean.getPassword(), "用户密码不能为空"));

        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }

        //检查有没有被注册过
        UserBean userBean = mUserBeanService.selectByPhone(loginBean.getPhoneNumber());
        if (userBean == null) {
            responseJSON.setMsg("请先注册");
            return responseJSON;
        }

        //检查密码
        if (!userBean.getUserPassword().equals(MD5Util.string2MD5(loginBean.getPassword()))) {
            responseJSON.setMsg("用户名或密码错误");
            return responseJSON;
        }

        // 判断redis是否有该用户 ,如果有则重新设置 失效时间
        List<String> liststr = JedisUtil.getAllAppUserToken();
        System.out.println(liststr);
        String token = "";
        if (liststr.size() > 0) {
            for (int i = 0; i < liststr.size(); i++) {
                String key = liststr.get(i);
                int uid = Integer.valueOf(JedisClientUtil.getString(key));
                if (userBean.getUserId() == uid) {
                    // 如果有则重新设置 失效时间,将原来的token返回
                    JedisUtil.setTokenTime(key);
                    token = key.substring(FinalData.APP_TOKEN.length(), key.length());
                    break;
                }
            }
        }

        if (CheckStringEmptyUtils.IsEmpty(token)) {
            token = RandomUtils.getRandom(20, RandomUtils.NUMBER_LETTER);
            JedisUtil.saveAppToken(token, userBean.getUserId());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("UserId", userBean.getUserId());
        map.put("UserBalance", userBean.getUserBalance());
        map.put("UserEducation", userBean.getUserEducation());
        map.put("UserJob", userBean.getUserJob());
        map.put("UserName", userBean.getUserName());
        map.put("UserPic", userBean.getUserPic());
        map.put("UserSchool", userBean.getUserSchool());
        map.put("token", token);
        responseJSON = ResponseUtils.getSuccessResponseBean("登录成功", map);
        return responseJSON;
    }


    /**
     * 重置密码接口
     *
     * @param forgotPasswordBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ResponseJSON ForgotPassword(@RequestBody ForgotPasswordBean forgotPasswordBean) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("重置失败", null);
        String checkStringList = CheckStringEmptyUtils.CheckStringList(new CheckStringEmptyUtils.CheckStringBean(forgotPasswordBean.getPhoneNumber(), "手机号码不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(forgotPasswordBean.getPassword(), "密码不能为空"),
                new CheckStringEmptyUtils.CheckStringBean(forgotPasswordBean.getMessageCode(), "短信验证码不能为空"));
        if (!checkStringList.equals(CheckStringEmptyUtils.ListSuccess)) {
            responseJSON.setMsg(checkStringList);
            return responseJSON;
        }
        //检查有没有被注册过
        UserBean userBeans = mUserBeanService.selectByPhone(forgotPasswordBean.getPhoneNumber());
        if (userBeans == null) {
            responseJSON.setMsg("该手机号未注册");
            return responseJSON;
        }
        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", forgotPasswordBean.getPhoneNumber());
        map.put("password", MD5Util.string2MD5(forgotPasswordBean.getPassword()));
        int i = mUserBeanService.updatePassword(map);
        if (i == 0) {
            responseJSON.setMsg("重置失败");
            return responseJSON;
        }
        responseJSON = ResponseUtils.getSuccessResponseBean("重置成功,请登录");
        return responseJSON;
    }
}
