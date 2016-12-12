<template>
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="glyphicon glyphicon-star"></i> 
             Collect Agent列表 
            <span class="badge">{{pagination.total}}</span>
        </div>
        <div class="table-responsive">
        	<table id="agentCollege" class="table table-hover table-striped">
                <thead>
                    <tr>
                       <th v-for="column in Columns">{{column}}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="agent in Data">
                        <td>{{agent.customer_name}}</td>
                        <td>{{agent.mac}}</td>
                        <td>{{agent.register_time}}</td>
                        <td v-if="agent.status==0" class="red">离线</td>
                        <td v-else="agent.status==1" class="blue">在线</td>
                        <td v-if="agent.is_active==0" class="blue">正常</td>
                        <td v-else="agent.is_active==1" class="red">禁用</td>
                        <td>{{agent.healthy_check_interval}}</td>
                        <td>{{agent.ip}}</td>
                        <td>{{agent.sync_data_interval}}</td>
                        <td>{{agent.dca_name}}</td>
                        <td>{{agent.get_task_interval}}</td>
                        <td>{{agent.last_request_time}}</td>
                        <td>
                            <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" @click="list(agent)">查看</button>
                            <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" @click="list(agent)">编辑</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        	<div class="panel-footer" align="center">
        		<pagination :pagination="pagination" :callback="getData" :options="paginationOptions"></pagination>
        	</div>
          </div>
           <modal id="myModal" :agent="agent" :callback="getData"></modal>
        </div>
    </template>

<script>
import pagination from '../../common/pagination.vue'
import modal from '../agent_detail/modal_dialog.vue'
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
                Columns: ['企业名','MAC地址','启动时间','状态','DCA是否禁用','健康检查间隙','IP地址','同步数据时间间隔','DCA名称','任务时间间隙','上次DCA请求时间','操作'],
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
                agent: {
                    id:'',
                    customer_name:'',
                    mac:'',
                    register_time:'',
                    status:'',
                    is_active:'',
                    healthy_check_interval:'',
                    ip:'',
                    sync_data_interval:'',
                    dca_name:'',
                    last_request_time:'',
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
				this.$http.get('http://120.77.86.1:8888/DCA/getDCAList',options).then((res)=>{
					this.Data = res.data.data;
                    this.pagination.total = res.data.total;
                    this.pagination.per_page = res.data.page_per_num;
                    this.pagination.last_page = Math.ceil(this.pagination.total/this.pagination.per_page);
				},(err)=>{
					console.log(res.data)
				})
			},
            list(agent){
                this.agent.id = agent.id;
                this.agent .customer_name = agent.customer_name;
                this.agent.mac = agent.mac;
                this.agent.register_time = agent.register_time;
                this.agent.status = agent.status;
                this.agent.is_active = agent.is_active;
                this.agent.healthy_check_interval = agent.healthy_check_interval;
                this.agent.ip = agent.ip;
                this.agent.sync_data_interval = agent.sync_data_interval;
                this.agent.dca_name = agent.dca_name;
                this.agent.get_task_interval = agent.get_task_interval;
                this.agent.last_request_time = agent.last_request_time;
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