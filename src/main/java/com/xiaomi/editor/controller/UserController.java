package com.xiaomi.editor.controller;

import com.xiaomi.editor.bean.UserBean;
import com.xiaomi.editor.paramsbean.RegisterBean;
import com.xiaomi.editor.service.IUserBeanService;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
        List<UserBean> userBeans = mUserBeanService.selectByPhone(registerBean.getPhone());
        if (userBeans != null && userBeans.size() != 0) {
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


}
