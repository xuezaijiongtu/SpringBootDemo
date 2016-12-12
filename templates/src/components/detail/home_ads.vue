<template>
<div class="container">
 <blockquote><p>欢迎使用，POI-Tech云平台</p></blockquote>
 <stat-view></stat-view>
 <list-view></list-view>
  </div>
</template>

<script>
	import statView from './ads_detail/statView.vue'
	import listView from './ads_detail/listView.vue'

	export default{
		components:{
			statView,listView
		}
	}
</script>

<style>
.container{
	text-align: left;
}
</style>