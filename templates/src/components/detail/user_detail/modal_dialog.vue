<template>
   <!-- Modal -->
        <div class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-plus-sign"></i> 查看/编辑用户信息</h4>
            </div>
            <div class="modal-body">
              <div class="row gap">
                <label class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="user.name" readonly>
                </div>
              </div>
              <div class="row gap"> 
                <label class="col-sm-2 control-label">邮箱地址</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" placeholder="" v-model="user.email" disabled>
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">所属企业名</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="user.customer_name" readonly>
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">用户昵称</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="user.nickname" readonly>
                </div>
              </div>
              <div class="row gap"> 
                <label class="col-sm-2 control-label">状态</label>
                <div class="col-sm-10">
                  <input type="radio" value="0" v-model="user.status" disabled>
                  <label>正常</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" value="1" v-model="user.status" disabled>
                  <label>禁用</label>
                </div>
              </div>
        
              <div class="row gap"> 
                <label class="col-sm-2 control-label">上次登录时间</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="user.last_login_time">
                </div>
              </div>
              <div class="row gap">
                <label class="col-sm-2 control-label">用户密码</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" placeholder="" v-model="user.pwd">
                </div>
              </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" @click="editUser(user)" >保存</button>
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
                <button type="button" id="submitbutton" class="btn btn-primary btn-sm" @click='editUser(user)'>确定</button>
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
    user:Object,
    callback: {
      type: Function,
      required: true
    }
  },
  methods:{
    editUser(user){
      let options = {
        params:{
          id:this.user.id
            }
          };
        this.$http.get('',options).then((res)=>{
              console.log(res.data.msg);
              this.callback();
        },(err)=>{
          console.log(res.data)
        })
      },
    alertModal(){
      var ts = document.getElementsByClassName('modal-alert');
      console.log(ts);
      ts.modal('show');
      }
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