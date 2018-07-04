## 编审小米 移动端API文档
### 1、接口说明
**接口请求说明：除特殊接口统一添加请求头"Authorization"<br>
特殊接口：<br>
1、App用户注册：api/user/register<br>
2、获取短信验证码：api/message/getMessageCode<br>
code:200 成功   100失败<br>
msg:接口说明<br>
BaseUrl:http://home.hbhanzhi.com:7052/api/<br>
API版本：1.0.0(初稿)<br>
更新时间：2018/6/28<br>**


### 2、API
#### 2.1 用户模块
##### 2.1.1 用户模块注册
函数调用地址：user/register  <br>
请求方式：POST<br>
函数入参：<br>
```
"phone":"15284224245"，String  		(必传)(前端限制最多11位)
"password":"123456789" String		(必传)(前端限制6-16位)
"messageCode":"123456"   //短信验证码 (必传)
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"注册成功"
}
```

#### 2.2 短信验证码模块
##### 2.2.1 获取短信验证码
函数调用地址：message/getMessageCode  <br>
请求方式：POST<br>
函数入参：<br>
```
"phoneNumber":"15284224245"，String  	 (必传)(前端限制最多11位)
"messageType":1 int		(必传)1：注册  2：找回密码
```
函数出参：
```
{
    "code":200,
    "data":"123456",
    "msg":"发送成功"
}
```