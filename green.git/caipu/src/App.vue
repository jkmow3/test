<template>
	<div>
		<el-container>
			<el-header>
				<el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect"
					background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
					<el-menu-item index="1">处理中心</el-menu-item>
					<el-menu-item index="3">消息中心</el-menu-item>
					<el-menu-item index="4">订单管理</el-menu-item>
				</el-menu>
			</el-header>
			<!-- 头部包括导航栏，login，以及logo -->
			<el-container>
				<el-header style="height:150px;margin-top:20px;">
					<el-form ref="form" label-width="80px">
						<el-form-item label="关键字">
							<el-input v-model="keyword"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="getCaipu(1)">搜索菜品</el-button>
						</el-form-item>
					</el-form>
					<div class="block">
						<el-pagination @current-change="currentChange" @prev-click="prevClick" @next-click="nextClick()"
							layout="prev, pager, next" :page-size="num" :total="total">
						</el-pagination>
					</div>
				</el-header>
				<el-container>
					<el-main>
						<el-container>
							<el-table :data="list" style="width: 100%">
								<el-table-column prop="name" label="菜品名称" width="180">
								</el-table-column>
								<el-table-column prop="tag" label="类型" width="180">
								</el-table-column>
								<el-table-column prop="content" label="介绍">
								</el-table-column>
							</el-table>
						</el-container>
					</el-main>
					<!-- ISC证书，网站备案号，如果是电商，需要有国家的经营许可证 -->
				</el-container>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		name: 'App',
		data() {
			return {
				activeIndex: '1',
				activeIndex2: '1',
				reverse: true,
				keyword: '',
				list: [],
				num: 0,
				total: 0

			}
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			getCaipu: function(number) {
				var _this = this;
				axios({
					url: 'http://localhost:3000?keyword=' + _this.keyword + '&num=' + number,
					method: 'get'
				}).then(function(res) {
					_this.list = JSON.parse(res.data.body).result.list;
					console.log(JSON.parse(res.data.body).result)
					_this.num = JSON.parse(res.data.body).result.num;
					_this.total = JSON.parse(res.data.body).result.total;

				})
			},
			currentChange: function(num) {
				this.getCaipu(num);
			},
			prevClick: function() {
				// console.log('prevClick');
			},
			nextClick: function() {
				// console.log('nextClick');
			},
			listenerFunction() {
				document.addEventListener('scroll', this.handleScroll, true);
			},
			handleScroll() {
				// console.log(window.pageYOffset)
				console.log(document.body.clientHeight - window.pageYOffset)
				if((document.body.clientHeight - window.pageYOffset) < 1000){
					console.log('页面高度不够了');
				}
			}
		},
		created(){
			this.listenerFunction();
		}
	}
</script>

<style>
	body {
		margin: 0px;
	}
</style>
