## 编审小米 后台系统API文档
### 1、接口说明
**接口请求说明：除特殊接口统一添加请求头"Authorization"<br>
特殊接口：<br>
1、system/admin/systemLogin<br>
code:200 成功   100失败<br>
msg:接口说明<br>
BaseUrl:http://home.hbhanzhi.com:7052/system/<br>
API版本：1.0.0(初稿)<br>
更新时间：2018/6/28<br>**


**超级管理员：<br>**
**轮播图模块<br>**
**公告模块<br>**
**工作室模块<br>**
**热门服务(商品)<br>**
**增加新用户(系统用户)<br>**

**工作室用户：<br>**
**修改自己店铺的信息<br>**
**商品模块<br>**


**订单模块<br>**
**工作室的评论列表<br>**
**具体商品的评论列表<br>**


### 2、API
#### 2.1 超级管理员模块
##### 2.1.1 系统用户登录
函数调用地址：admin/systemLogin  <br>
请求方式：POST<br>
函数入参：<br>
```
"userLoginName":"admin"，String  		(必传)(前端限制最多12位)
"userPassword":"123456789" String		(必传)(前端限制6-16位)
"systemUserType":1//用户类型 (必传)  1：超级管理员  2：工作室
```
函数出参：
```
{
    "code":200,
    "data":{
        "systemUserId":1,//用户id
        "token":"Y05dGDw7EGTW4dxYK8v5"
    },
    "msg":"登录成功"
}
```
##### 2.1.2 添加工作室用户

函数调用地址：admin/addSysteamUser <br>
请求方式：POST<br>
函数入参：<br>

```
"userLoginName":"xiaomiStudio"，String  (必传)(前端限制最多12位)
"userPassword":"123456789" String		(必传)(前端限制6-16位)
```

函数出参：

```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

##### 2.1.3 修改密码

函数调用地址：admin/updateSystemPassword <br>
请求方式：POST<br>
函数入参：<br>
```
"userPassword":"123456789" String			(必传)(前端限制6-16位)
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"修改成功"
}
```

##### 2.1.4 获取工作室全部用户分页
函数调用地址：admin/getSystemUserList<br>请求方式：POST<br>函数入参：<br>
```
"page":1，int  (必传)获取第几页  从1开始
"size":10，int  (必传)每页获取多少条
"searchContent":""，String  模糊查询条件
```
函数出参：
```
{
    "code":200,
    "data":{ 
        "endRow":1, 
        "firstPage":1,//第一页
        "hasNextPage":false,//是不是有下一页
        "hasPreviousPage":false,//是不是有上一页
        "isFirstPage":true,//是不是第一页
        "isLastPage":true,//是不是最后一页
        "lastPage":1,//最后一页页码
        "list":[
            {
                "systemUserLoginname":"xiaomi",//登录名称
                "systemUserid":2//用户id
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":1,
        "navigatePages":8,
        "navigatepageNums":[//页码列表
            1
        ],
        "nextPage":0,
        "pageNum":1,//现在请求的是第几页
        "pageSize":2,//当前页数量
        "pages":1,//总共有多少页
        "prePage":0,
        "size":1,
        "startRow":1,
        "total":1//总共有多少条
    },
    "msg":"查询成功"
}
```

##### 2.1.5 删除工作室用户
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

##### 2.1.6 添加轮播图
函数调用地址：admin/addBanner <br>
请求方式：POST<br>
函数入参：<br>
```
"bannerWebUrl":"http://www.baidu.com"，String  (必传)轮播图的外部连接
"bannerWeight":1，int  (必传)轮播图权重,用于轮播图的展示顺序  越大排序越在前边
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

##### 2.1.7 获取轮播图列表
函数调用地址：admin/getBannerList<br>
请求方式：POST<br>
函数入参：<br>

```
"page":1，int  (必传)获取第几页  从1开始
"size":10，int  (必传)每页获取多少条
```
函数出参：
```
{
    "code":200,
    "data":{
        "endRow":2,
        "firstPage":1,
        "hasNextPage":false,
        "hasPreviousPage":false,
        "isFirstPage":true,
        "isLastPage":true,
        "lastPage":1,
        "list":[
            {
                "banner_id":3,//轮播图id
                "banner_weight":4,//权重
                "banner_url":"http://image.baidu.com/search/detail?ct=503316480",//图片地址
                "banner_web_url":"https://blog.csdn.net/qq_15037349/article/details/72832306"//外部连接地址
            },
            {
                "banner_id":2,
                "banner_weight":3,
                "banner_url":"http://image.baidu.com/search/detail?ct=503316480",
                "banner_web_url":"https://blog.csdn.net/hgg923/article/details/54616287"
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":1,
        "navigatePages":8,
        "navigatepageNums":[
            1
        ],
        "nextPage":0,
        "pageNum":1,
        "pageSize":2,
        "pages":1,
        "prePage":0,
        "size":2,
        "startRow":1,
        "total":2
    },
    "msg":"查询成功"
}
```

##### 2.1.8 删除轮播图
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
    "data":{ 
        "endRow":1, 
        "firstPage":1,//第一页
        "hasNextPage":false,//是不是有下一页
        "hasPreviousPage":false,//是不是有上一页
        "isFirstPage":true,//是不是第一页
        "isLastPage":true,//是不是最后一页
        "lastPage":1,//最后一页页码
        "list":[
            {
                "bannerDel":0,
                "bannerWebUrl":"http://www.baidu.com",//外部连接地址
                "bannerWeight":100,//权重
                "bannerUrl":"http://www.XXX.XX.JPG",//图片地址
                "bannerId":2//轮播图id
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":1,
        "navigatePages":8,
        "navigatepageNums":[//页码列表
            1
        ],
        "nextPage":0,
        "pageNum":1,//现在请求的是第几页
        "pageSize":2,//当前页数量
        "pages":1,//总共有多少页
        "prePage":0,
        "size":1,
        "startRow":1,
        "total":1//总共有多少条
    },
    "msg":"查询成功"
}
```
##### 2.1.9 修改轮播图信息
函数调用地址：admin/updateBanner <br>
请求方式：POST<br>
函数入参：<br>
```
"bannerId":1 (必传)轮播图id
"bannerWebUrl":"http://www.baidu.com"，String   轮播图的外部连接
"bannerWeight":1，int   轮播图权重,用于轮播图的展示顺序  越大排序越在前边
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


##### 2.1.10 发布公告
函数调用地址：admin/addNotice <br>
请求方式：POST<br>
函数入参：<br>
```
"noticeTitle":"公告标题"，String  (必传)(前端限制20位)
"noticeContent":"公告内容"，String  (必传)(前端限制1000位)
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"添加成功"
}
```

##### 2.1.11 获取公告列表
函数调用地址：admin/getNoticeList <br>
请求方式：POST<br>
函数入参：<br>
```
"page":1，int  (必传)获取第几页  从1开始
"size":10，int  (必传)每页获取多少条
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
                "noticeId":11,//公告id
                "noticeTime":"2018-06-27 17:47:22",//发表时间
                "noticeTitle":"公告标题10"//公告标题
            },
            {
                "noticeContent":"公告内容9",
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


##### 2.1.12 删除公告
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

##### 2.1.13 添加工作室
函数调用地址：admin/addStudio <br>
请求方式：POST<br>
函数入参：<br>
```
"systemUserid":1，int  (必传)系统用户id(谁开的这家店)
"studioName":"小米工作室"，String  (必传)工作室名称(前端限制最多6字)
"studioMoney":1000，folat  (必传)工作室押金 (前端限制上限十万)
"studioPhone":"15284224245"，int  (必传)联系电话(前端限制最多11位)
"studioQq":"531195555"，int  (必传)工作QQ(前端限制最多15位)
"studioBriefintroduction":"这是一家店铺"，int  (必传)简介(前端限制最多100位)
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

##### 2.1.14 修改工作室

函数调用地址：admin/updateStudioData<br>
请求方式：POST<br>
函数入参：<br>

```
"studioId":1,int (必传) 工作室id
"systemUserid":1，int  (必传)系统用户id(谁开的这家店)
"studioName":"小米工作室"，String   工作室名称(前端限制最多6字)
"studioMoney":1000，folat   工作室押金(前端限制上限十万)
"studioPhone":"15284224245"，int  联系电话(前端限制最多11位)
"studioQq":"531195555"，int   工作QQ(前端限制最多15位)
"studioBriefintroduction":"这是一家店铺"，int  简介(前端限制最多100位)
"file" File   工作室头像，上传图片，使用文件流上传  
```

函数出参：

```
{
    "code":200,
    "data":null,
    "msg":"修改成功"
}
```

##### 2.1.15 获取工作室详情

函数调用地址：admin/getStudioData<br>
请求方式：POST<br>
函数入参：<br>

```
"studioId":1，int  (必传)系统用户id(谁开的这家店)
```

函数出参：

```
{
    "code":200,
    "data":{
        "studioCollectionNmuber":0,//收藏数量
        "studioMonthlySales":0,//月销售量
        "studioMoney":10000,//押金
        "studioBriefintroduction":"这是一家小米店铺",//店铺简介
        "studioQq":"531192555",//联系QQ
        "systemUserid":2,//系统用户id(法人id)
        "studioName":"xiaomiStudio",店铺名称
        "studioPhone":"15284224244",//联系电话
        "studioPic":"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=e9449e382d9759ee4a5067cd8ac0242b/94cad1c8a786c9179e80a80cc23d70cf3bc75700.jpg"//头像图片
    },
    "msg":"获取成功"
}
```

##### 2.1.16 获取工作室列表

函数调用地址：admin/getStudioList <br>
请求方式：POST<br>
函数入参：<br>
```
"page":1，int  (必传)获取第几页  从1开始
"size":10，int  (必传)每页获取多少条
"searchContent":""，String  (必传)模糊搜索条件
```
函数出参：
```
{
    "code":200,
    "data":{
        "endRow":1,
        "firstPage":1,
        "hasNextPage":false,
        "hasPreviousPage":false,
        "isFirstPage":true,
        "isLastPage":true,
        "lastPage":1,
        "list":[
            {
                "studio_name":"xiaomiStudio",//工作室名称
                "studio_phone":"15284224244",//联系电话
                "studio_briefIntroduction":"这是一家小米店铺1",//简介
                "studio_QQ":"531192555",//QQ
                "system_userid":2,//系统用户id
                "studio_id":1,//工作室id
                "studio_monthly_sales":0,//月销量
                "studio_collection_nmuber":0,//收藏数量
                "studio_money":10000,//押金
                "studio_pic":"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=e9449e382d9759ee4a5067cd8ac0242b/94cad1c8a786c9179e80a80cc23d70cf3bc75700.jpg"//图片
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":1,
        "navigatePages":8,
        "navigatepageNums":[
            1
        ],
        "nextPage":0,
        "pageNum":1,
        "pageSize":2,
        "pages":1,
        "prePage":0,
        "size":1,
        "startRow":1,
        "total":1
    },
    "msg":"查询成功"
}
```
##### 2.1.17 删除工作室
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

##### 2.1.19 获取工作室的全部商品

函数调用地址：admin/getCommodityListByStudioId<br>
请求方式：POST<br>
函数入参：<br>

```
"studioId":1 int  (必传) 工作室id
"page":1，int  (必传)页码
"size":1，int  (必传)每页多少条
```

函数出参：

```
{
    "code":200,
    "data":{
        "endRow":2,
        "firstPage":1,
        "hasNextPage":false,
        "hasPreviousPage":false,
        "isFirstPage":true,
        "isLastPage":true,
        "lastPage":1,
        "list":[
            {
                "commodity_original_price":1,//商品原价
                "commodity_collection_quantity":0,//收藏数量
                "commodity_id":1,//商品id
                "commodity_introduce":"大红袍茶叶",//商品简介
                "commodity_hot":0,//是否为热门商品  1：热门  0：非热门
                "commodity_pic":"http://www.xxx.jpg",//图片
                "commodity_pics":"http://www.xxx.jpg,http://www.xxx.jpg,http://www.xxx.jpg",//详情图片逗号分隔
                "commodity_type":1,//商品类型 1:查重、2:降重、3:速审
                "studio_id":1,//工作室id
                "commodity_present_price":1,//现在价格
                "commodity_name":"大红袍"//名称
            },
            {
                "commodity_original_price":1,
                "commodity_collection_quantity":0,
                "commodity_id":2,
                "commodity_introduce":"菊花茶叶",
                "commodity_hot":0,
                "commodity_pic":"http://www.xxx.jpg",
                "commodity_pics":"http://www.xxx.jpg,http://www.xxx.jpg,http://www.xxx.jpg",
                "commodity_type":1,
                "studio_id":1,
                "commodity_present_price":1,
                "commodity_name":"菊花"
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":1,
        "navigatePages":8,
        "navigatepageNums":[
            1
        ],
        "nextPage":0,
        "pageNum":1,
        "pageSize":10,
        "pages":1,
        "prePage":0,
        "size":2,
        "startRow":1,
        "total":2
    },
    "msg":"查询成功"
}
```

##### 2.1.20 获取工作室的全部非热门商品

函数调用地址：admin/getNotHotCommodityListByStudioId<br>
请求方式：POST<br>
函数入参：<br>

```
"studioId":1 int  (必传) 工作室id
"page":1，int  (必传)页码
"size":1，int  (必传)每页多少条
```

函数出参：

```
{
    "code":200,
    "data":{
        "endRow":2,
        "firstPage":1,
        "hasNextPage":false,
        "hasPreviousPage":false,
        "isFirstPage":true,
        "isLastPage":true,
        "lastPage":1,
        "list":[
            {
                "commodity_original_price":1,//商品原价
                "commodity_collection_quantity":0,//收藏数量
                "commodity_id":1,//商品id
                "commodity_introduce":"大红袍茶叶",//商品简介
                "commodity_hot":0,//是否为热门商品  1：热门  0：非热门
                "commodity_pic":"http://www.xxx.jpg",//图片
                "commodity_pics":"http://www.xxx.jpg,http://www.xxx.jpg,http://www.xxx.jpg",//详情图片逗号分隔
                "commodity_type":1,//商品类型 1:查重、2:降重、3:速审
                "studio_id":1,//工作室id
                "commodity_present_price":1,//现在价格
                "commodity_name":"大红袍"//名称
            },
            {
                "commodity_original_price":1,
                "commodity_collection_quantity":0,
                "commodity_id":2,
                "commodity_introduce":"菊花茶叶",
                "commodity_hot":0,
                "commodity_pic":"http://www.xxx.jpg",
                "commodity_pics":"http://www.xxx.jpg,http://www.xxx.jpg,http://www.xxx.jpg",
                "commodity_type":1,
                "studio_id":1,
                "commodity_present_price":1,
                "commodity_name":"菊花"
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":1,
        "navigatePages":8,
        "navigatepageNums":[
            1
        ],
        "nextPage":0,
        "pageNum":1,
        "pageSize":10,
        "pages":1,
        "prePage":0,
        "size":2,
        "startRow":1,
        "total":2
    },
    "msg":"查询成功"
}
```



##### 2.1.21 获取全部热门商品

函数调用地址：admin/getHotCommodityList<br>
请求方式：POST<br>
函数入参：<br>

```
"page":1，int  (必传)页码
"size":1，int  (必传)每页多少条
```

函数出参：

```
{
    "code":200,
    "data":{
        "endRow":2,
        "firstPage":1,
        "hasNextPage":false,
        "hasPreviousPage":false,
        "isFirstPage":true,
        "isLastPage":true,
        "lastPage":1,
        "list":[
            {
                "commodity_original_price":1,//商品原价
                "commodity_collection_quantity":0,//收藏数量
                "commodity_id":1,//商品id
                "commodity_introduce":"大红袍茶叶",//商品简介
                "commodity_hot":1,//是否为热门商品  1：热门  0：非热门
                "commodity_pic":"http://www.xxx.jpg",//图片
                "commodity_pics":"http://www.xxx.jpg,http://www.xxx.jpg,http://www.xxx.jpg",//详情图片逗号分隔
                "commodity_type":1,//商品类型 1:查重、2:降重、3:速审
                "studio_id":1,//工作室id
                "commodity_present_price":1,//现在价格
                "commodity_name":"大红袍"//名称
            },
            {
                "commodity_original_price":1,
                "commodity_collection_quantity":0,
                "commodity_id":2,
                "commodity_introduce":"菊花茶叶",
                "commodity_hot":1,
                "commodity_pic":"http://www.xxx.jpg",
                "commodity_pics":"http://www.xxx.jpg,http://www.xxx.jpg,http://www.xxx.jpg",
                "commodity_type":1,
                "studio_id":1,
                "commodity_present_price":1,
                "commodity_name":"菊花"
            }
        ],
        "navigateFirstPage":1,
        "navigateLastPage":1,
        "navigatePages":8,
        "navigatepageNums":[
            1
        ],
        "nextPage":0,
        "pageNum":1,
        "pageSize":10,
        "pages":1,
        "prePage":0,
        "size":2,
        "startRow":1,
        "total":2
    },
    "msg":"查询成功"
}
```

##### 2.1.22 设置热门服务(就是热门商品)

函数调用地址：admin/setHotCommodity <br>
请求方式：POST<br>
函数入参：<br>
```
"CommodityId":1，int  (必传)商品id
"CommodityHot":1，int  (必传)1:设置热门服务  0:取消设置
```
函数出参：
```
{
    "code":200,
    "data":null,
    "msg":"设置成功"
}
```


#### 2.2 工作室用户登录

##### 2.2.1 修改自己工作室数据

函数调用地址：studio/updateStudioData <br>
请求方式：POST<br>
函数入参：<br>

```
"studioId":1，int  (必传)工作室id
"studioName":"xiaomiStudio"，String  工作室名称(前端限制最多6字)
"phone":"15284224245"，String  工作室电话(前端限制最多11位)
"qq":"531192555"，String  工作室QQ(前端限制最多15位)
"studioBriefintroduction":"这里是工作室简介"，String   (前端限制最多100位)
"file":，File  工作室图片
```

函数出参：

```
{
    "code":200,
    "data":null,
    "msg":"设置成功"
}
```

##### 2.2.2 添加商品

函数调用地址：studio/addCommodity<br>
请求方式：POST<br>
函数入参：<br>

```
"studioId":1 int (必传) 工作室id
"commodityName":"大红袍" String (必传)   商品名称 (前端限制最多50位)
"commodityIntroduce":"大红袍大红茶" String (必传)    商品介绍 (前端限制最多300位)
"commodityPresentPrice":110.00 Folat (必传)   商品价格
"commodityType":1  int (必传) 商品类型  (1:查重、2:降重、3:速审)
"file": File (必传)   商品头像
"files": File[] (必传)    商品详情图片
```

函数出参：

```
{
    "code":200,
    "data":null,
    "msg":"设置成功"
}
```

##### 2.2.3 查询某个商品的详情

函数调用地址：studio/queryCommodityById<br>
请求方式：POST<br>
函数入参：<br>

```
"commodityId":1 int (必传) 商品id
```

函数出参：

```
{
    "code":200,
    "data":{
        "commodity_original_price":1,
        "commodity_collection_quantity":0,
        "commodity_id":1,
        "commodity_hot":0,
        "commodity_introduce":"大红袍茶叶",
        "commodity_pic":"http://www.xxx.jpg",
        "commodity_pics":"http://www.xxx.jpg,http://www.xxx.jpg,http://www.xxx.jpg",
        "commodity_type":1,
        "studio_id":1,
        "commodity_present_price":1,
        "commodity_name":"大红袍"
    },
    "msg":"查询成功"
} 
```

##### 2.2.4 修改商品

函数调用地址：studio/updateCommodity<br>
请求方式：POST<br>
函数入参：<br>

```
"commodityId":1 int (必传) 商品id
"commodityName":"大红袍" String     商品名称 (前端限制最多50位)
"commodityIntroduce":"大红袍大红茶" String      商品介绍 (前端限制最多300位)
"commodityPresentPrice":110.00 Folat    商品价格
"commodityType":1  int   商品类型  (1:查重、2:降重、3:速审)
"file": File     商品头像
"files": File[]     商品详情图片
```

函数出参：

```
{
    "code":200,
    "data":null,
    "msg":"设置成功"
}
```

##### 2.2.5 下架商品

函数调用地址：studio/delCommodity<br>
请求方式：POST<br>
函数入参：<br>

```
"commodityId":1 int (必传) 商品id
```

函数出参：

```
{
    "code":200,
    "data":null,
    "msg":"下架成功"
}
```

##### 