接口地址:/login
接口描述:用户登陆
请求方式:GET
请求参数:
username 用户名
password 密码
code     状态码

返回:
{
	"status":true,
	"code":1,
	"msg":"登陆成功"
}

失败
{
	"status":false,
	"code":-1,
	"msg":"登陆失败"
}

code	  msg
1		登陆成功
-1		登陆失败
96		用户名或密码错误
97		验证码错误
98		用户名或密码错误
99		参数缺少或不合法