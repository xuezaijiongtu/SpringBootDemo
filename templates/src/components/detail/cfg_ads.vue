<template>
<div class="container">
	<!-- 当前路径 -->
	<ol class="breadcrumb">
	  <li><a href="#">首页</a></li>
	  <li class="active">系统设置</li>
	</ol>
	<!-- 当前路径 End -->
	<div class="col-sm-2"></div>

		<div class="col-sm-8">

		<div class="panel panel-default">
			<div class="panel-heading"><i class="glyphicon glyphicon-plus-sign"></i> 系统设置</div>
			<div class="panel-body">

				<!-- Nav Tabs -->
				<ul class="nav nav-tabs" role="tablist">
				  <li class="active"><a href="#info" role="tab" data-toggle="tab">基本设置</a></li>
				  <li><a href="#address" role="tab" data-toggle="tab">缓存设置</a></li>
				</ul>
				<!-- Nav Tabs End -->

				<!-- Tab Content -->
				<div class="tab-content">
				  
				  <!-- Tab 1 -->
				  <div class="tab-pane active" id="info">
					<form class="form-horizontal" role="form">
						<div class="row gap">
							<label class="col-sm-2 control-label"><span class="required">*</span> 用户名</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" placeholder="">
							</div>
						</div>
						<div class="row gap">
							<label class="col-sm-2 control-label">昵称</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" placeholder="">
							</div>
						</div>
						<div class="row gap">
							<label class="col-sm-2 control-label"><span class="required">*</span> 密码</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" placeholder="">
							</div>
						</div>
						<div class="row gap">
							<label class="col-sm-2 control-label"><span class="required">*</span> 重复密码</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" placeholder="">
							</div>
						</div>	
						<div class="row gap">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" placeholder="">
							</div>
						</div>
					</form>
				  </div>
				  <!-- Tab 1 End -->

				  <!-- Tab 2 -->
				  <div class="tab-pane" id="address">
					<form class="form-horizontal" role="form">
						<div class="row gap">
							<label class="col-sm-2 control-label"><span class="required">*</span> 联系人</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" placeholder="">
							</div>
						</div>
						<div class="row gap">
							<label class="col-sm-2 control-label"><span class="required">*</span> 地址</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" placeholder="">
							</div>
						</div>
						<div class="row gap">
							<label class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" placeholder="">
							</div>
						</div>
						<div class="row gap">
							<label class="col-sm-2 control-label"><span class="required">*</span> 邮编</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" placeholder="">
							</div>
						</div>
					</form>
				  </div>
			      <!-- Tab 2 End -->

				</div>
				<!-- Tab Content End -->

			</div>
			<div class="panel-footer" align="right">
				<button type="submit" class="btn btn-primary">创建</button>
			</div>
		</div>
		<div class="col-sm-2"></div>

	</div>

<div>
</template>

<script>
	
</script>

<style scoped>
	
</style>