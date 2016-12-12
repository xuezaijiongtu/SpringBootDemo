接口地址：/DCA/getDCA
接口描述：获取某个DCA信息
请求方式：GET
请求参数：
dca_id	//DCA自增ID	
返回：
{
  "msg": "获取DCA数据成功",
  "code": 1,
  "data": {
    "is_active": "1",
    "log_path": "/home/poit/logs/",
    "healthy_check_interval": 1000,
    "ip": "10.21.34.103",
    "sync_data_interval": 500,
    "dca_name": "node_one",
    "last_request_time": "2016-12-07 00:25:40",
    "id": 1,
    "customer_name": "jiadajia",
    "mac": "00-00-00-00-00",
    "register_time": "2016-12-02 09:25:17",
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
code	  msg
1     获取DCA数据成功
99		参数缺少或不合法



接口地址:/DCA/getDCAList
接口描述:获取DCA列表
请求方式:GET
请求参数:
page		//页码
返回:
{
  "total": 4,
  "data": [
    {
      "is_active": "0",
      "log_path": "C:/work",
      "healthy_check_interval": 88,
      "ip": "192.168.80.1",
      "sync_data_interval": 88,
      "last_request_time": "2016-12-07 05:53:01",
      "mac": "00-50-56-C0-00-08",
      "register_time": "2016-12-02 08:38:11",
      "get_task_interval": 88,
      "dca_name": "poitech_01",
      "id": 5,
      "customer_name": "poitech",
      "status": "0"
    }
  ],
  "page_per_num": 10,
  "page": 1
}

失败
{
  "msg": "参数缺少或不合法",
  "code": 99,
  "status": false
}

code	  msg
99		参数缺少或不合法



接口地址:/DCA/editDCA
接口描述:DCA信息修改
请求方式:GET
请求参数:
healthy_check_interval　//健康检查间隙
sync_data_interval			//同步数据时间间隔
is_active					      //DCA是否禁用，0表示正常，1表示禁用
get_task_interval			  //获取任务时间间隔
dca_id						      //DAC_ID
返回:
{
	"status":true,
	"code":1,
	"msg":"DAC信息更新成功"
}
失败
{
	"status":false,
	"code":99,
	"msg":"登陆失败"
}
code	  msg
1     DCA信息更新成功
0     DCA信息更新失败
99		参数缺少或不合法



数据字典：
id                  所属企业客户ID
ip                  IP地址
mac                 MAC地址
is_active           DCA是否禁用，0表示正常，1表示禁用
log_path            log存储路径
dca_name            DCA名称
last_request_time   上次DCA请求时间
customer_name       所属企业名
register_time       启动时间
status              状态，0表示离线，1表示在线
healthy_check_interval    健康检查间隙
sync_data_interval        同步数据时间间隔
