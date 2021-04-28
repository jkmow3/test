<template>
	<div>
		<div id="biao" style="width:600px;height:300px;"></div>
	</div>
</template>

<script>
	//	在这里引入echarts进行尝试
	import echarts from 'echarts'
	export default {
		name: 'News',
		methods: {
			//	实例化echarts对象
			getData: function() {
				//	获取页面中的echarts的div
				var myChart = echarts.init(document.getElementById('biao'));

				//	echarts图标的设置
				myChart.setOption({
					// 图表的标题
					title: {
						text: '我们班的同学未来的打算'
					},
					tooltip: {},
					// 横轴上有的数据
					xAxis: {
						data: ["考研", "考公务员", "考教师资格证", "找工作", "继承家业", "出国留学"]
					},
					yAxis: {},
					series: [{
						name: '人数',
						type: 'bar',
						data: [12, 1, 1, 30, 2, 2]
					}]
				});
			}
		},
		mounted: function() {
			//	自动调用echarts的实例化函数
			this.getData();
		}
	}
</script>

<style>

</style>
