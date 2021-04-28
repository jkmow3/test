<template>
	<div>
		<div class="row">
			<div @click="getHeroData(item.hero_id)" v-for="(item,index) in list" :key="index" class="card" style="width: 18rem;">
				<img :src="item.cover" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">{{ item.name }}</h5>
					<p class="card-text">{{ item.hero_id }}</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	//	引入axios
	import axios from 'axios';
	export default {
		name: 'list',
		data() {
			return {
				list: []
			}
		},
		beforeMount: function() {
			var _this = this;
			axios({
				url: 'http://localhost:3000/date',
				method: 'get'
			}).then(function(res) {
				_this.list = JSON.parse(res.data.body).list;
			})
		},
		methods: {
			getHeroData: function(num) {
				axios({
					url: 'http://localhost:3000/hero?keyword=' + num,
					method: 'get'
				}).then(function(res) {
					console.log(JSON.parse(res.data.body));
				})
			}
		}
	
	}
</script>

<style>
</style>
