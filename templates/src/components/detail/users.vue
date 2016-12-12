<template>
<div class="container">

	<div class="row">
		<div class="col-sm-5">
			<!-- 当前路径 -->
			<ol class="breadcrumb">
			  <li><a href="#"><i class="glyphicon glyphicon-export"></i> 首页</a></li>
			  <li><a href="#">用户管理</a></li>
			  <li class="active">用户列表</li>
			</ol>
			<!-- 当前路径 End -->
		</div>
		<div class="col-sm-7 al-r">

			<!-- 工具栏 -->
			<div class="btn-group">
			    <button class="btn btn-default"><i class="glyphicon glyphicon-plus"></i> 新建用户</button>
			    <button class="btn btn-default btn-search"  @click='searchUnfold'><i class="glyphicon glyphicon-search"></i> 高级搜索</button>
			</div>
			<!-- 工具栏 End -->
		</div>
	</div>
    
	<!-- 条件查询 -->
	<div class="panel panel-default panel-search">
		<div class="panel-heading">查询条件</div>
		<div class="panel-body">
			<form name="searchform" action="#">
				<div class="row gap">
					<label class="col-sm-1">ID</label>
					<div class="col-sm-2" data-name="id" data-role="eidtarea">
						<input type="text" class="form-control" value="" placeholder="ID">
					</div>
					<label class="col-sm-1">用户名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="" placeholder="用户名">
					</div>
					<label class="col-sm-1">所属企业</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" value="" placeholder="ID">
					</div>
					<label class="col-sm-1">状态</label>
					<div class="col-sm-2">
						<select>
							<option value=""></option>
							<option value="0">禁用</option>
							<option value="1">验证</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<div class="panel-footer" align="right">
			<button class="btn btn-primary" type="submit">查询</button>
			<button class="btn btn-default">取消</button>
			<button class="btn btn-danger btn-sm" data-role="delbutton"><i class="glyphicon glyphicon-trash"></i> 删除</button>
		</div>
	</div>
	<!-- 条件查询 End -->

	<!-- 列表 -->
	<table-tool></table-tool>
	<!-- 列表 End -->

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
</template>
<script>
	import tableTool from './user_detail/table.vue'
	export default{
		components:{
			tableTool
		},
		methods:{
			searchUnfold(){
				console.log('test');
				$(".panel-search").toggle();
			}
		}
	}
</script>
<style></style>