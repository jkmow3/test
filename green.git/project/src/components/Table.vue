<template>
    <el-container>
        <el-header>
            <el-row>
                <el-col :span="12">
                    <el-select
                            @change="changePro()"
                            v-model="value"
                            placeholder="请选择">
                        <el-option
                                v-for="(item,index) in pro"
                                :key="item.name"
                                :value="index"
                                :label="item.name"
                        >
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="12">
                    <el-select
                            @change="changeCity()"
                            v-model="cityVal"
                            placeholder="请选择">
                        <el-option
                                v-for="(item,index) in this.city.children"
                                :key="item.name"
                                :value="index"
                                :label="item.name"
                        >
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
        </el-header>
        <el-main>
            <el-table
                    :data="tableData">
                <el-table-column
                        prop="date"
                        label="日期"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="地址">
                </el-table-column>
            </el-table>
        </el-main>
    </el-container>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return {
                tableData: [],
                value:'',
                pro:[],
                cityVal:'',
                city:{}
            }
        },
        methods:{
            changePro:function(){
                this.city = this.pro[this.value]
            },
            changeCity:function(){
                console.log(this.city.children[this.cityVal]);
            }
        },
        mounted() {
            var _this = this;
            axios({
                url:'http://localhost:3000/disease',
                method:'get'
            }).then(function(res){
                _this.pro = JSON.parse(JSON.parse(res.data.body).data).areaTree[0].children;
            })
        }
    }
</script>
