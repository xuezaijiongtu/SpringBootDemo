接口地址：/User/getUserList
接口描述：获取用户列表数据
请求方式：GET
请求参数：
page		页数
返回：
{
  "total": 2,
  "data": [
    {
      "last_login_time": "2016-11-24 16:21:34",
      "name": "笔记",
      "nickname": "笔记",
      "id": 2,
      "customer_name": "poitech",
      "pwd": "123456",
      "email": "84853839@qq.com",
      "age": 23,
      "status": "0"
    },
    {
      "last_login_time": "2016-11-23 16:15:14",
      "name": "刘小兴",
      "nickname": "学在囧途",
      "id": 1,
      "customer_name": "jiadajia",
      "pwd": "123456",
      "email": "33673540@qq.com",
      "age": 23,
      "status": "0"
    }
  ],
  "page_per_num": 10,
  "page": 1
}
失败：
{
  "msg": "参数缺少或不合法",
  "code": 99,
  "status": false
}
code		msg
99		参数缺少或不合法



接口地址：/User/getUser
接口描述：获取用户列表数据
请求方式：GET
请求参数：
user_id		用户id
返回：
{
  "msg": "获取User数据成功",
  "code": 1,
  "data": {
    "last_login_time": "2016-11-23 16:15:14",
    "name": "刘小兴",
    "nickname": "学在囧途",
    "id": 1,
    "customer_name": "jiadajia",
    "pwd": "123456",
    "email": "33673540@qq.com",
    "age": 23,
    "status": "0"
  },
  "status": true
}
失败：
{
  "msg": "参数缺少或不合法",
  "code": 99,
  "data": null,
  "status": false
}
code		msg
1   获取User数据成功
99		参数缺少或不合法



数据字典：
total             总数    
last_login_time   上次登录时间
name              用户名
nickname          用户昵称
id                用户自增ID
customer_name     所属企业名
pwd               用户密码
email             邮箱地址
age               年龄
status            是否可用,1为禁用，0为正常
page_per_num      每页数据量
page              页数
