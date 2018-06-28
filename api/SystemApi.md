##编审小米 后台系统API文档
###1、接口说明
**接口请求说明：除特殊接口统一添加请求头"Authorization"<br>
特殊接口：<br>
1、system/admin/systemLogin<br>
code:200 成功   100失败<br>
msg:接口说明<br>
BaseUrl:http://home.hbhanzhi.com:7052/system/<br>
API版本：1.0.0(初稿)<br>
更新时间：2018/6/27<br>**


超级管理员：<br>
轮播图模块<br>
公告模块<br>
工作室模块<br>
热门服务(商品)<br>
增加新用户(系统用户)<br>

工作室用户：<br>
修改自己店铺的信息<br>
商品模块<br>


订单模块<br>
工作室的评论列表<br>
具体商品的评论列表<br>


###2、API
####2.1 超级管理员模块
#####2.1.1 系统用户登录
函数调用地址：admin/systemLogin  <br>
请求方式：POST<br>
函数入参：<br>
```
"userLoginName":"admin"，String  		(必传)
"userPassword":"123456789" String		(必传)
```
函数出参：
```
{
    "code":200,
    "data":{
        "systemUserId":1,//用户id
        "systemUserType":1,//用户类型  1：超级管理员  2：工作室
        "token":"Y05dGDw7EGTW4dxYK8v5"
    },
    "msg":"登录成功"
}
```
#####2.1.2 修改密码
函数调用地址：admin/updateSystemPassword <br>
请求方式：POST<br>
函数入参：<br>
```
"userPassword":"123456789" String			(必传)
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"修改成功"
}
```

#####2.1.3 添加工作室用户
函数调用地址：admin/addSysteamUser <br>
请求方式：POST<br>
函数入参：<br>
```
"userLoginName":"xiaomiStudio"，String  (必传)
"userPassword":"123456789" String		(必传)
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

#####2.1.4 获取工作室全部用户分页(暂时不要调用)
函数调用地址：admin/getSysteamUserList <br>
请求方式：POST<br>
函数入参：<br>
```
"page":1，int  (必传)获取第几页  从1开始
"pageSize":10，int  (必传)每页获取多少条
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

#####2.1.5 删除工作室用户
函数调用地址：admin/delSystemUser <br>
请求方式：POST<br>
函数入参：<br>
```
"userId":"2"，String  (必传)要删除的用户id
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"删除成功"
}
```

#####2.1.6 添加轮播图
函数调用地址：admin/addBanner <br>
请求方式：POST<br>
函数入参：<br>
```
"webUrl":"http://www.baidu.com"，String  (必传)轮播图的外部连接
"weight":1，int  (必传)轮播图权重,用于轮播图的展示顺序  越大排序越在前边
"file":File  (必传)上传图片，使用文件流上传
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

#####2.1.7 获取轮播图列表(暂时不要调用，没有实现)
函数调用地址：admin/getBannerList <br>
请求方式：POST<br>
函数入参：<br>
```
"page":1，int  (必传)获取第几页  从1开始
"pageSize":10，int  (必传)每页获取多少条
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

#####2.1.8 删除轮播图
函数调用地址：admin/delBanner <br>
请求方式：POST<br>
函数入参：<br>
```
"bannerId":1，int  (必传)轮播图id
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"删除成功"
}
```
#####2.1.9 修改轮播图信息
函数调用地址：admin/updateBanner <br>
请求方式：POST<br>
函数入参：<br>
```
"bannerId":1 (必传)轮播图id
"webUrl":"http://www.baidu.com"，String   轮播图的外部连接
"weight":1，int   轮播图权重,用于轮播图的展示顺序  越大排序越在前边
"file":File   上传图片，使用文件流上传
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"删除成功"
}
```


#####2.1.10 发布公告
函数调用地址：admin/addNotice <br>
请求方式：POST<br>
函数入参：<br>
```
"noticeTitle":"公告标题"，String  (必传)
"noticeContent":"公告内容"，String  (必传)
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

#####2.1.11 获取公告列表(暂时不要调用，没有实现)
函数调用地址：admin/getNoticeList <br>
请求方式：POST<br>
函数入参：<br>
```
"page":1，int  (必传)获取第几页  从1开始
"pageSize":10，int  (必传)每页获取多少条
```
函数出参：
```
{
    "code":200,
    "data":{
        "endRow":2,//下一页有多少条
        "firstPage":1,//第一页
        "hasNextPage":true,//是不是有下一页
        "hasPreviousPage":false,//是不是有上一页
        "isFirstPage":true,//是不是第一页
        "isLastPage":false,//是不是最后一页
        "lastPage":6,//最后一页页码
        "list":[
            {
                "noticeContent":"公告内容10", //公告内容
                "noticeDel":0,
                "noticeId":11,//公告id
                "noticeTime":"2018-06-27 17:47:22",//发表时间
                "noticeTitle":"公告标题10"//公告标题
            },
            {
                "noticeContent":"公告内容9",
                "noticeDel":0,
                "noticeId":10,
                "noticeTime":"2018-06-27 17:47:14",
                "noticeTitle":"公告标题9"
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":6,
        "navigatePages":8,
        "navigatepageNums":[//页码列表
            1,
            2,
            3,
            4,
            5,
            6
        ],
        "nextPage":2,//下一页页码
        "pageNum":1,//现在请求的是第几页
        "pageSize":2,//当前页数量
        "pages":6,
        "prePage":0,
        "size":2,
        "startRow":1,
        "total":11//总共有多少条
    },
    "msg":"查询成功"
}
```


#####2.1.12 删除公告
函数调用地址：admin/delNotice <br>
请求方式：POST<br>
函数入参：<br>
```
"noticeId":1，int  (必传)公告id
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"删除成功"
}
```

#####2.1.13 添加工作室
函数调用地址：admin/addStudio <br>
请求方式：POST<br>
函数入参：<br>
```
"studioUserId":1，int  (必传)系统用户id(谁开的这家店)
"studioName":"小米工作室"，String  (必传)工作室名称(前端限制最多6字)
"studioMoney":1000，folat  (必传)工作室押金
"studioPhone":"15284224245"，int  (必传)联系电话
"studioQQ":"531195555"，int  (必传)工作QQ
"studioBriefintroduction":"这是一家店铺"，int  (必传)简介
"file" File (必传) 工作室头像，上传图片，使用文件流上传
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

#####2.1.14 添加工作室
函数调用地址：admin/addStudio <br>
请求方式：POST<br>
函数入参：<br>
```
"studioUserId":1，int  (必传)系统用户id(谁开的这家店)
"studioName":"小米工作室"，String  (必传)工作室名称(前端限制最多6字)
"studioMoney":1000，folat  (必传)工作室押金
"studioPhone":"15284224245"，int  (必传)联系电话
"studioQQ":"531195555"，int  (必传)工作QQ
"studioBriefintroduction":"这是一家店铺"，int  (必传)简介
"file" File (必传) 工作室头像，上传图片，使用文件流上传
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```
#####2.1.15 获取工作室列表(暂时不要调用，没有实现)
函数调用地址：admin/addStudio <br>
请求方式：POST<br>
函数入参：<br>
```
"page":1，int  (必传)获取第几页  从1开始
"pageSize":10，int  (必传)每页获取多少条
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```
#####2.1.16 删除工作室
函数调用地址：admin/delStudio <br>
请求方式：POST<br>
函数入参：<br>
```
"studioId":1，int  (必传)studioId
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"删除成功"
}
```

#####2.1.17 设置热门服务(就是热门商品)
函数调用地址：admin/setHotCommodity <br>
请求方式：POST<br>
函数入参：<br>
```
"CommodityId":1，int  (必传)商品id
"HotCommodity":1，int  (必传)1:设置热门服务  0:取消设置

```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"设置成功"
}
```