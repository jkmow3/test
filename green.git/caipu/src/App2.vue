<template>
	<div id="app" class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">实训练习</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li  @click="changeList('caipu')" class="nav-item active">
						<a class="nav-link" href="#">菜谱大全 <span class="sr-only">(current)</span></a>
					</li>
					<li  @click="changeList('weather')" class="nav-item">
						<a class="nav-link" href="#">天气预报</a>
					</li>
					<li  @click="changeList('news')" class="nav-item">
						<a class="nav-link" href="#">新闻</a>
					</li>
				</ul>
			</div>
		</nav>
		<!-- 使用caipu组件 -->
		<caipu v-if="list == 'caipu'" />
		<Tianqi v-if="list == 'weather'" />
		<News v-if="list == 'news'" />
	</div>
</template>

<script>
	//	import 导入 某个组件 从 ...
	import caipu from './components/caipu.vue';
	// 导入天气组件
	import Weather from './components/Weather.vue';
	import News from './components/News.vue'

	export default {
		name: 'App',
		data() {
			return {
				list: 'news'
			}
		},
		methods: {
			changeList: function(str) {
				this.list = str
			}
		},
		components: {
			caipu,
			Tianqi: Weather,
			News
		}
	}
</script>

<style>

</style>
