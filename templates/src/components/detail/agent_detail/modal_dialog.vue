<template>
   <!-- Modal -->
        <div class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-plus-sign"></i> 查看/编辑DCA信息</h4>
            </div>
            <div class="modal-body">
              <div class="row gap">
                <label class="col-sm-2 control-label">企业名</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.customer_name" readonly>
                </div>
              </div>
              <div class="row gap"> 
                <label class="col-sm-2 control-label">IP地址</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.ip" disabled>
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">MAC地址</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.mac" readonly>
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">启动时间</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.register_time" readonly>
                </div>
              </div>
              <div class="row gap"> 
                <label class="col-sm-2 control-label">状态</label>
                <div class="col-sm-10">
                  <input type="radio" value="0" v-model="agent.status" disabled>
                  <label>离线</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" value="1" v-model="agent.status" disabled>
                  <label>在线</label>
                </div>
              </div>

              <div class="row gap">
                <label class="col-sm-2 control-label">DCA是否禁用</label>
                <div class="col-sm-10">
                  <input type="radio" value="0" v-model="agent.is_active">
                  <label>正常</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" value="1" v-model="agent.is_active">
                  <label>禁用</label>
                </div>
              </div>
              <div class="row gap"> 
                <label class="col-sm-2 control-label">健康检查间隙</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.healthy_check_interval">
                  </div>
              </div>        
              <div class="row gap"> 
                <label class="col-sm-2 control-label">同步数据时间间隔</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.sync_data_interval">
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">任务时间间隙</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.get_task_interval">
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">DCA名称</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.dca_name" disabled>
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">上次DCA请求时间</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="agent.last_request_time" disabled>
                </div>
              </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" @click="editDCA(agent)" >保存</button>
            </div>
          </div>
        </div>
            <!-- 提示模态框 -->
    <div class="modal fade modal-alert" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-body">是否确定删除？</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
            <button type="button" data-role="submitbutton" class="btn btn-primary btn-sm">确定</button>
          </div>
        </div>
      </div>
    </div>
  <!-- 提示模态框 End -->
      </div>
    <!--Modal End -->

</template>
<script>
export default{
  props:{
    agent:Object,
    callback: {
      type: Function,
      required: true
    }
  },
  methods:{
    editDCA(agent){
      let options = {
        params:{
          dca_id:this.agent.id,
          healthy_check_interval:this.agent.healthy_check_interval,
          sync_data_interval:this.agent.sync_data_interval,
          is_active:this.agent.is_active,
          get_task_interval:this.agent.get_task_interval,
            }
          };
        this.$http.get('http://120.77.86.1:8888/DCA/editDCA',options).then((res)=>{
              console.log(res.data.msg);
              this.callback();
        },(err)=>{
          console.log(res.data)
        })
      },
  }
}
</script>

<style>
.modal{
  top:50px;
}
.modal-backdrop{
  display: none;
}
</style>