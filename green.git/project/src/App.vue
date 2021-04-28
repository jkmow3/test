<template>
  <div id="app">
    <!--    如果要修改他的样式，可以使用.el-container-->
    <el-container>
      <el-aside width="200px">
        <el-col>
          <el-menu
                  :router="bol"
                  default-active="/"
                  class="el-menu-vertical-demo"
                  @open="handleOpen"
                  @close="handleClose"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
            <el-menu-item index="999">
            </el-menu-item>
            <el-menu-item index="/">
                <i class="el-icon-menu"></i>
                <span slot="title">数据汇总</span>
            </el-menu-item>
            <el-menu-item index="/table">
              <i class="el-icon-menu"></i>
              <span slot="title">地区信息</span>
            </el-menu-item>
            <el-menu-item index="/echarts">
                <i class="el-icon-menu"></i>
                <span slot="title">图表展示</span>
            </el-menu-item>
            <el-menu-item index="/search">
              <i class="el-icon-document"></i>
              <span slot="title">搜索详细</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-aside>
      <el-container>
        <el-header style="padding:0px;">
          <el-menu
                  :default-active="activeIndex2"
                  class="el-menu-demo"
                  mode="horizontal"
                  @select="handleSelect"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
            <el-menu-item index="1">武汉工程大学数媒1班XXX</el-menu-item>
            <el-menu-item index="2">数字化可视系统</el-menu-item>
          </el-menu>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  // 引入axios
  // import axios from 'axios'
  export default {
    name: 'app',
    data(){
      return {
        availHeight:0,
        activeIndex2:'1',
        bol:true
      }
    },
    components: {

    },
    beforeMount() {
      // axios({
      //   url:'http://localhost:3000/list_total',
      //   method:'get'
      // }).then(function(){
      //  //  console.log(JSON.parse(res.data.body));
      // })
      /*
      *
      *    医疗
      *
      *    国内疫苗
      * */

    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style>
  body,html{
    margin:0px;
    height:100%;
  }
  #app,.el-container,.el-aside,.el-col,.el-menu{
    height:100%;
  }

  .el-header, .el-footer {
    color: #333;
    text-align: center;
    line-height: 60px;
  }


</style>
