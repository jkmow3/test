<template>
	<el-container>
		<el-main>
			<el-row>
				<el-alert title="国内新增" type="error">
				</el-alert>
			</el-row>
			<el-row>
				<el-table :data="add" style="width: 100%">
					<el-table-column prop="confirm" label="confirm">
					</el-table-column>
					<el-table-column prop="dead" label="dead">
					</el-table-column>
					<el-table-column prop="importedCase" label="importedCase">
					</el-table-column>
					<el-table-column prop="localConfirm" label="localConfirm">
					</el-table-column>
					<el-table-column prop="noInfect" label="noInfect">
					</el-table-column>
					<el-table-column prop="nowConfirm" label="nowConfirm">
					</el-table-column>
				</el-table>
			</el-row>
			<el-row>
				<el-col :span="24">
					<div style="width:100%;height:300px;" id="zhu"></div>
				</el-col>
			</el-row>
		</el-main>
	</el-container>
</template>

<script>
	import axios from "axios";
	import * as echarts from 'echarts';
	export default {
		name: "Home",
		data() {
			return {
				total: {},
				add: [],
				addarr: [],
				addnum: []
			}
		},
		methods: {
			getData: function() {
				var _this = this;
				axios({
					url: 'http://localhost:3000/disease',
					method: 'get'
				}).then(function(res) {
					_this.total = JSON.parse(JSON.parse(res.data.body).data).chinaTotal;
					_this.add = [JSON.parse(JSON.parse(res.data.body).data).chinaAdd];
					var addObj = JSON.parse(JSON.parse(res.data.body).data).chinaAdd;
					console.log(addObj);
					var addarr = [];
					var addnum = []
					//  对象的遍历
					//  在遍历之前，需要检查右边是不是对象
					//  这种写法对后台的数据要求非常严格
					for (var item in addObj) {
						addarr.push(item);
						addnum.push(addObj[item]);
					}
					console.log(addarr);
					_this.addarr = addarr;
					_this.addnum = addnum;
					_this.drawZhu();
				})
			},
			drawZhu: function() {
				var _this = this;
				var myChart = echarts.init(document.getElementById('zhu'));
				myChart.setOption({
					xAxis: {
						//  图的类别
						type: 'category',
						data: _this.addarr
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						data: _this.addnum,
						type: 'bar',
						showBackground: true,
						backgroundStyle: {
							color: 'rgba(180, 180, 180, 0.2)'
						}
					}]
				})
			}
		},
		// beforeCreate   页面的标签创建之前
		// created        页面的标签创建之后
		// beforeMount    页面的标签里面的内容渲染完毕之前
		// mounted
		mounted() {
			this.getData();

		}
	}
</script>

<style scoped>

</style>
