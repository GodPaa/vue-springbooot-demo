<template>
    <el-container>
        <!--    侧边栏    -->
        <el-aside width="200px" class="el-aside">
            <el-menu :default-openeds="['1']">
                <el-sub-menu index="1">
                    <template #title>
                        <el-icon><message /></el-icon>User表
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="1-1">
<!--                            <router-link to="/member/level/1">会员等级</router-link>-->
                            <router-link :to="{name:'MemberLevel',params:{id:2}}">xxx1</router-link>
                        </el-menu-item>
                        <el-menu-item index="1-2">
                            <router-link to="/member/list">xx2</router-link>
                        </el-menu-item>
                        <el-menu-item index="1-3">
                            <router-link :to="{name:'Main',params:{name:'admin'}}">xx3</router-link>
                        </el-menu-item>
                    </el-menu-item-group>
                </el-sub-menu>
                <el-sub-menu index="2">
                    <template #title>
                        <el-icon><message /></el-icon>course表
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="1-1">Option 1</el-menu-item>
                        <el-menu-item index="1-2">Option 2</el-menu-item>
                    </el-menu-item-group>
                </el-sub-menu>
            </el-menu>
        </el-aside>

    <el-container>
        <!--   顶部     -->
        <el-header style="text-align: right; font-size: 12px" class="el-header">
            <el-dropdown>
                <el-icon style="margin-right: 15px"><setting /></el-icon>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item>View</el-dropdown-item>
                        <el-dropdown-item>Add</el-dropdown-item>
                        <el-dropdown-item>Delete</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
            <!-- 从login页面传入的用户名 -->
            <span>{{$route.params.name}}</span>
<!--                <span>admin</span>-->
        </el-header>

        <!--  主体     -->
        <el-main>
            <router-view/>
            <el-table :data="tableData">

                <!--数据-->
                <el-table-column prop="id" label="用户ID" width="140"></el-table-column>
                <el-table-column prop="username" label="用户名" width="120"></el-table-column>
                <el-table-column prop="password" label="用户密码"> </el-table-column>

                <!-- 操作 -->
                <el-table-column align="right">
                    <template #header>
                        <!-- 根据名字查找 -->
                        <el-input v-model="searchId" size="mini" style="width: 200px" placeholder="根据ID搜索" />
                        <el-button type="primary" size="mini" style="margin-left: 5px" @click="searchUser">查询</el-button>
                    </template>

                    <template #default="scope">
                        <!-- 编辑 -->
                        <el-button type="mini" @click="showEditView(scope.$index,scope.row)">Edit</el-button>

                        <!-- 删除 -->
                        <el-popconfirm title="确定删除吗" @confirm="handleDelete(scope.row.id)">
                            <template #reference>
                                <el-button size="mini" type="danger">删除</el-button>
                            </template>
                        </el-popconfirm>


                    </template>
                </el-table-column>

            </el-table>

            <div class="demo-pagination-block">
                <el-pagination
                        v-model:currentPage="currentPage"
                        :page-sizes="[5,10,20]"
                        :page-size="pageSize"
                        layout="sizes, prev, pager, next"
                        :total="total"
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                >
                </el-pagination>
            </div>

            <!-- 弹出框 -->
            <el-dialog v-model="dialogVisible" title="编辑信息" width="20%">
                <div>
                    <el-tag>用户名称</el-tag>
                    <el-input v-model="updateData.username" size="small" placeholder="username" style="width: 200px; margin-left: 8px" ></el-input>
                    <br>
                    <br>
                    <el-tag>用户密码</el-tag>
                    <el-input v-model="updateData.password" size="small" placeholder="password" style="width: 200px; margin-left: 8px" ></el-input>
                </div>

                <template #footer>
                      <span class="dialog-footer">
                        <el-button size="small" @click="dialogVisible = false">Cancel</el-button>
                        <el-button size="small" type="primary" @click="handleUpdate()">Confirm</el-button>
                      </span>
                </template>
            </el-dialog>

        </el-main>
    </el-container>  <!-- 右边container end -->

    </el-container>  <!--  -->
</template>

<script>
    import request from "../utils/request";

    export default {
        name: "Main",
        data(){
            return{
                tableData:[],
                updateData:{
                    id:'',
                    username:'',
                    password:''
                },
                searchId:'',
                dialogVisible:false,
                currentPage: 1,
                pageSize: 10,
                total: 0
            }
        },
        mounted() {
          this.load();
        },
        methods:{
            // 初始化表格 ( 弃用 )
            initTableData(){
                console.log("我拿到数据了")
                request.get("/users").then(res => {
                    if (res){
                        console.log(res.data)
                        this.tableData = res.data;
                    }
                })
            },
            // 根据名字查找
            searchUser(){
                request.get("/users/" + this.searchId).then(resp=>{
                    if (resp.code === 200) {
                        console.log(resp.data);
                        this.tableData = [];
                        this.tableData = resp.data
                        // this.tableData = resp.data;
                    }
                })
            },
            // 更新
            handleUpdate(){
                request.put("/users",this.updateData).then(resp=>{
                    if (resp){
                        this.load()
                        this.dialogVisible = false;
                    }
                })
            },
            // 根据id删除
            handleDelete(id) {
                console.log(id)
                request.delete("/users/" + id).then(res => {
                    if (res.code === 200) {
                        this.$message({
                            type: "success",
                            message: "删除成功"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    this.load()  // 删除之后重新加载表格的数据
                })
            },
            showEditView(id,data){
                this.dialogVisible = true;
                this.updateData = data;

            },
            // 分页
            handleSizeChange(pageSize) {   // 改变当前每页的个数触发
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {  // 改变当前页码触发
                this.currentPage = pageNum
                this.load()
            },
            // 加载数据/重载
            load(){
                request.get("/usersPage", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize
                    }
                }).then(res => {
                    this.tableData = res.data.records
                    this.total = res.data.total
                })
            }


        }



    }
</script>

<style scoped>
    .el-header {
        background-color: #b3c0d1;
        color: var(--el-text-color-primary);
        line-height: 60px;
    }

    .el-aside {
        color: var(--el-text-color-primary);
    }
</style>