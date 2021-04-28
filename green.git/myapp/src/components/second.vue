<template>
	<div class="row">
		<div @click="getHeroData(item.hero_id)" v-for="(item,index) in list" :key="index" class="card"
			style="width: 18rem;">
			<img :src="item.cover" class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">{{ item.name }}</h5>
				<p class="card-text">{{ item.hero_id }}</p>
			</div>
		</div>
	</div>
	<div class="modal" v-if="showModal"   id="motai" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">{{ heroData.title }}</h5>
					<button @click="getHeroData()" type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h5>{{ heroData.name }}</h5>
					<p>{{ heroData.background_story }}</p>
				</div>
				<div class="modal-footer">
					<p>{{ heroData.melee_tips }}</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	//	引入axios
	import axios from 'axios';
	export default {
		name: 'App',
		data() {
			return {
				list: [],
				heroData:{},
				showModal:false
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
				var _this = this;
				_this.showModal = !_this.showModal;
				axios({
					url: 'http://localhost:3000/hero?keyword=' + num,
					method: 'get'
				}).then(function(res) {
					_this.heroData = JSON.parse(res.data.body).info
					console.log(JSON.parse(res.data.body).info);
				})
			}
		}
	
	}
</script>

<style>
</style>
