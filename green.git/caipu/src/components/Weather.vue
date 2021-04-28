<template>
	<div>
		<h1>天气预报<span class="badge badge-secondary">New</span></h1>
		<div class="input-group mb-3">
			<input type="text" class="form-control" placeholder="请输入你的城市">
			<div class="input-group-append">
				<span class="input-group-text" id="basic-addon2">搜索</span>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">日期</th>
					<th scope="col">风向</th>
					<th scope="col">温度</th>
					<th scope="col">天气</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</template>

<script>
	export default {
		name: 'Weather',
	}
</script>

<style>

</style>
