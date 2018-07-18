package com.xiaomi.editor.controller.app;

import com.xiaomi.editor.paramsbean.MessageBean;
import com.xiaomi.editor.system.ResponseJSON;
import com.xiaomi.editor.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-04<br>
 * Time: 15:38<br>
 * UpdateDescription：<br>
 */
@RequestMapping("api/message")
@Controller
public class MessageController {

    /**
     * 获取短信验证码
     *
     * @param messageBean
     * @return
     */
    @RequestMapping(value = "/getMessageCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJSON getMessageCode(@RequestBody MessageBean messageBean) {
        ResponseJSON responseJSON = ResponseUtils.getFiledResponseBean("发送失败");
        if (CheckStringEmptyUtils.IsEmpty(messageBean.getPhoneNumber())) {
            responseJSON.setMsg("手机号码不能为空");
            return responseJSON;
        }
        //根据手机号去获取验证码,看看存在不存在
        String registerCode = JedisUtil.getRegisterCode(messageBean.getPhoneNumber());
        if (!CheckStringEmptyUtils.IsEmpty(registerCode)) {
            //存在,去看时间
            String[] split = registerCode.split(",");
            if (split.length == 2) {
                //现在的时间>那个时间之前不能重复获取
                long time = System.currentTimeMillis();
                if (time - Long.parseLong(split[1]) < 0) {
                    responseJSON.setMsg(FinalData.MESSAGE_CODE_REPEAT_TIME + "s内不能重复发送");
                    return responseJSON;
                } else {//更改验证码和时间
                    String random = RandomUtils.getRandom(6, RandomUtils.NUMBER_LETTER);
                    JedisUtil.updateRegisterCode(registerCode, random, new Date().getTime() + (FinalData.MESSAGE_CODE_REPEAT_TIME * 1000));
                    responseJSON = ResponseUtils.getSuccessResponseBean("发送成功", random);
                    return responseJSON;
                }
            }
        } else {//不存在，直接添加
            String random = RandomUtils.getRandom(6, RandomUtils.NUMBER_LETTER);
            JedisUtil.saveRegisterCode(messageBean.getPhoneNumber(), random, new Date().getTime() + (FinalData.MESSAGE_CODE_REPEAT_TIME * 1000));
            responseJSON = ResponseUtils.getSuccessResponseBean("发送成功", random);
        }
        return responseJSON;
    }


}
