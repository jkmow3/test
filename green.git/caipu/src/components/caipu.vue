<template>
	<div>
		<div class="jumbotron">
			<h1 class="display-4">菜谱大全</h1>
			<p class="lead">好好做饭，养胖自己</p>
			<hr class="my-4">
			<div class="form-group">
				<label for="search">请输入关键字</label>
				<input type="email" class="form-control" id="search" aria-describedby="emailHelp" v-model="keyword">
			</div>
			<a @click="getData" class="btn btn-primary btn-lg" role="button">搜索</a>
		</div>
		<div>
			<ul class="list-group">
				<!-- vue在遍历数组的时候，曾经要求，每次遍历都需要添加一个key属性，现在已经没用了，但是插件还是在检测 -->
				<li class="list-group-item" v-for="(item,index) in list" :key="index">
					<h3>{{ item.name }}</h3>
					<p v-html="item.content"></p>
					<img :src="item.pic">

					<div class="row">
						<!-- 便利item里面的material -->
						<div class="col-sm" :key="indexs" v-for="(items,indexs) in item.material">
							<p>{{ items.mname }}</p>
							<p>{{ items.amount }}</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
	// 在这里引入其他组件
	import axios from 'axios';
	export default {
		name: 'caipu',
		data() {
			return {
				keyword: '',
				list: []
			}
		},
		methods: {
			//	写一个函数，点击之后，需要输出关键字
			getData: function() {
				var _this = this;
				axios({
					method: 'get',
					url: 'http://localhost:3000?keyword=' + _this.keyword,
				}).then(function(res) {
					_this.list = JSON.parse(res.data.body).result.list;
				})
			}
		}
	}
</script>

<style>

</style>
