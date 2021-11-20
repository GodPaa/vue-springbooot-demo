​								Vue项目搭建常用的配置文件request.js和vue.config.js



1. request.js用来请求数据，封装的代码如下：

```js
import axios from 'axios'
import router from "@/router";

const request = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 5000
})

// 请求白名单，如果请求在白名单里面，将不会被拦截校验权限
const whiteUrls = ["/user/login", '/user/register']

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // 取出sessionStorage里面缓存的用户信息
    let userJson = sessionStorage.getItem("user")

    if (!whiteUrls.includes(config.url)) {  // 校验请求白名单
        if(!userJson) {
            router.push("/login")
        } else {
            let user = JSON.parse(userJson);
            config.headers['token'] = user.token;  // 设置请求头
        }
    }
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // 验证token
        if (res.code === '401') {
            console.error("token过期，重新登录")
            router.push("/login")
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request


```

使用

```js
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
```

2. vue.config.js

   ```js
   // 跨域配置
   module.exports = {
       devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
           port: 8081,
           proxy: {                 //设置代理，必须填
               '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                   target: 'http://localhost:8080',     //代理的目标地址
                   changeOrigin: true,              //是否设置同源，输入是的
                   pathRewrite: {                   //路径重写
                       '/api': ''                     //选择忽略拦截器里面的单词
                   }
               }
           }
       }
   }
   
   ```

   





