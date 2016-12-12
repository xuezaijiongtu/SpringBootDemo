<template>
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="glyphicon glyphicon-list"></i> 
            &nbsp; 用户信息 &nbsp; 
            <span class="badge">{{pagination.total}}</span>
        </div>

        <div class="table-responsive">
        	<table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th><input class="js-checkall-inline" id="allCheck" type="checkbox" @click="checkAll"></th>
                        <th v-for="column in Columns">{{column}}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="user in Data">
                        <td><input class="js-checkall-inline" name="check" type="checkbox"></td>
                        <td>{{user.id}}</td>
                        <td>{{user.name}}</td>
                        <td>{{user.email}}</td>
                        <td>{{user.customer_name}}</td>
                        <td>{{user.nickname}}</td>
                        <td>{{user.pwd}}</td>
                        <td>{{user.age}}</td>
                        <td v-if="user.status==0" class="blue">正常</td>
                        <td v-else="user.status==1" class="red">禁用</td>
                        <td>{{user.last_login_time}}</td>
                        <td>
                            <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" @click="list(user)">查看</button>
                            <button class="btn btn-default btn-sm"data-toggle="modal" data-target="#myModal" @click="list(user)">编辑</button>
                            <button class="btn btn-danger btn-sm" data-role="delbutton"><i class="glyphicon glyphicon-trash"></i> 删除</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        	<div class="panel-footer" align="center">
        		<pagination :pagination="pagination" :callback="getData" :options="paginationOptions"></pagination>
        	</div>
        </div>
        <modal id="myModal" :user="user" :callback="getData"></modal>
    </div>
</template>

<script>
import pagination from '../../common/pagination.vue'
import modal from '../user_detail/modal_dialog.vue'
	export default{
        components:{
            pagination,modal
        },
		props:{
			url:String,
			page:{
				type:Number,
				default:1
			}
		},
		data:function(){
			return {
                Columns: ['用户ID','用户名','邮箱地址','所属企业名','用户昵称','用户密码','年龄','状态','上次登录时间','操作'],
                Data:[],
                pagination: {
                    total: 0,   //数据集总量
                    per_page: 2,    // required 每页多少
                    current_page: 1, // required  当前页数
                    last_page: 1,    // required  最后一页
                    from: 1,     //from
                    to: 1           // required
                  },
                paginationOptions: {
                    offset: 2,  //左右
                    previousText: 'Prev',
                    nextText: 'Next',
                    alwaysShowPrevNext: true
                    },
                user:{
                    id:'',
                    name:'',
                    email:'',
                    customer_name:'',
                    nickname:'',
                    pwd:'',
                    age:'',
                    status:'',
                    last_login_time:''
                 } 
                }
            },
		created(){
			this.getData()
		},
		methods:{
			getData (){
             let options = {
                params:{
                    page:this.pagination.current_page
                    }
                };
				this.$http.get('http://120.77.86.1:8888/User/getUserList',options).then((res)=>{
					this.Data = res.data.data;
                    this.pagination.total = res.data.total;
                    // this.pagination.per_page = res.data.page_per_num;
                    this.pagination.per_page = 2;

                    this.pagination.last_page = Math.ceil(this.pagination.total/this.pagination.per_page);
				},(err)=>{
					console.log(res.data)
				})
			},
            list(user){
                this.user.id = user.id;
                this.user.name = user.name;
                this.user.email = user.email;
                this.user.customer_name = user.customer_name;
                this.user.nickname = user.nickname;
                this.user.pwd = user.pwd;
                this.user.age = user.age;
                this.user.status = user.status;
                this.user.last_login_time = user.last_login_time;
            },
            checkAll(){
                var ocheck = document.getElementById('allCheck');
                if(ocheck.checked){ 
                    $("input[name='check']").attr('checked', true) 
                }else { 
                    $("input[name='check']").attr('checked', false)
                }
            }
        },
      }
</script>

<style scope>
.table{
	text-align: center;
	white-space:nowrap;
}
.table > thead > tr > th {
	text-align: center;
	white-space:nowrap;
	width: 70px;
}
.red{
    color: red;
}
.blue{
    color: blue;
}
</style>