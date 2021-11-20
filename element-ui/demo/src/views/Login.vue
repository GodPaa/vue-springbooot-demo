<template>
    <div>

        <el-form ref="form" :model="form" :rules="rules" class="login-box">
            <h3 class="login-title">登录页面</h3>
            <el-form-item label="账号" prop="username">
                <el-input type="text" placeholder="请输入用户名" v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" placeholder="请输入密码" v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {ElMessage} from "element-plus";
    import request from "../utils/request";

    export default {
        name: "Login",
        data(){
            return{
                form:{
                    username:'',
                    password:'',
                },
                rules:{
                    username: [
                        {
                            required: true,
                            message: '请输入用户名',
                            trigger: 'blur',
                        },
                    ],
                    password: [
                        {
                            required: true,
                            message: '请输入密码',
                            trigger: 'blur',
                        },
                    ]
                }
            }
        },
        methods:{
            // 表单校验
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // post 请求
                        console.log("发送post")
                        console.log(this.form)
                        request.post("/login", this.form).then(res => {
                            if (res.code === 200) {
                                this.$message({
                                    type: "success",
                                    message: "登录成功"
                                })
                                console.log("登录成功")
                                // sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息

                                // 登录成功的时候更新当前路由
                                sessionStorage.setItem('isLogin','true');
                                // 弹窗
                                ElMessage({
                                    message: '登录成功',
                                    type: 'success',
                                })
                                // 跳转
                                // this.$router.push("/main")
                                this.$router.push({name:'Main',params:{name:this.form.username}})
                                console.log("我跳转了")
                            }else {
                                ElMessage({
                                    message: '用户名或密码错误',
                                    type: 'warning',
                                })
                                return false
                            }
                        })   // post请求
                    }  // isValid
                })
            },
        }
    }
</script>

<style lang="css" scoped>
    .login-box{
        width: 350px;
        margin: 150px auto;
        border: 1px solid #DCDFE6;
        padding: 50px;
        border-radius: 5px;
        box-shadow: 0 0 30px;
    }
    .login-title{
        text-align: center;
        margin-bottom: 40px;
    }
</style>