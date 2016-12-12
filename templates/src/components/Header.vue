<template>
	<!-- Header -->
	<header id="header">
		<div class="logo"><h4>POI-Tech云平台</h4></div>
		<!-- Main Menu -->
		<nav class="main-menu">
			<a href="./Hello.vue" data-id="m0" target="main-frame" class="on" ><i class="glyphicon glyphicon-home"></i> 首页</a>
		</nav>
		<!-- Menu End -->

		<!-- User -->
		<div class="username btn-group pull-right">
		  <a id="ts" type="button" class="user-btn dropdown-toggle" data-toggle="dropdown">
		    <i class="glyphicon glyphicon-user"></i> &nbsp; xing.liu <span class="caret"></span>
		  </a>
		  <ul class="dropdown-menu arrow" role="menu">
		    <li><a href="#">我的帐户</a></li>
		    <li><a href="#">修改密码</a></li>
		    <li class="divider"></li>
		    <li><a href="#">退出 <i class="glyphicon glyphicon-log-out"></i></a></li>
		  </ul>
		</div>
		<!-- User End -->

	</header>
</template>

<script>
export default {
      data() {
          return {
      }
     }
 };

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h4{
	margin-left: 16px;
	margin-top: 15px;
	color: white;
}
</style>
