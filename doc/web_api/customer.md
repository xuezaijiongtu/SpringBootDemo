接口地址:/Customer/getCustomerList
接口描述:获取用户列表
请求方式:GET
请求参数:
page	页码
返回:
{
  "total": 2,
  "data": [
    {
      "customer_key": "a6282b62e50e600628ca6781646added",
      "customer_mac": "11111",				
      "id": 2,		        
      "customer_name": "苹果",
      "customer_code": "apple"
    },
    {
      "customer_key": "weida_key",
      "customer_mac": "00-00-00-00-00",
      "id": 1,
      "customer_name": "xingshi",
      "customer_last_check_index": 2000,
      "customer_code": "xingshi"
    }
  ],
  "page_per_num": 10,
  "page": 1	
}
失败:
{
	"status":false,
	"code":99,
	"msg":"参数缺少或不合法"
}

code	  msg
99		参数缺少或不合法



接口地址:/Customer/checkCodeExist
接口描述:检查customer code是否存在
请求方式:GET
请求参数:
code	//customer code,客户代码
返回:
{
	"status":true,
	"code":1,
	"msg":"code未被使用，可用"
}
失败
{
	"status":false,
	"code":99,
	"msg":"参数缺少或不合法"
}
code	  msg
1		code未被使用，可用
0		code已被使用，不可用
99		参数缺少或不合法



接口地址:/Customer/addCustomer
接口描述:新增企业
请求方式:GET
请求参数:
name      //客户名称
code      //客户代码
mac		    //企业Mac地址
返回:
{
	"status":true,
	"code":1,
	"msg":"企业添加成功"
}
失败
{
	"status":false,
	"code":99,
	"msg":"参数缺少或不合法"
}
code	  msg
1		企业添加成功
0		企业添加失败
99		参数缺少或不合法



接口地址：/Customer/editCustomer
接口描述：更新企业信息
请求方法：GET
请求参数：
mac     //企业Mac地址 
id      //ID
返回：
{
	"status":true,
	"code":1,
	"msg":"企业信息更新成功"
}
失败：
{
	"status":false,
	"code":99,
	"msg":"参数缺少或不合法"
}
code	  msg
1		企业信息更新成功
0		企业信息更新失败
99		参数缺少或不合法



接口地址：/Customer/getCustomer
接口描述：根据ID获取企业信息
请求方法：GET
请求参数：
id      //ID
返回：
{
  "customer_key": "weida_key",
  "customer_mac": "00-00-00-00-00",
  "id": 1,
  "customer_name": "xingshi",
  "customer_last_check_index": 2000,
  "customer_code": "xingshi"
}
失败：
{
	"code":99,
	"msg":"参数缺少或不合法"
}
code	  msg
99		参数缺少或不合法



数据字典：
total             数据总数
customer_key      客户key
customer_mac      绑定企业mac地址
id            自增ID
customer_name     客户名称
customer_code     客户代码
customer_last_check_index 上次检查index位置，index表示客户表中的id
page_per_num      每页数据集数
page          页数