																		vue的环境搭建

**一、安装node.js**

官网：https://nodejs.org/en/download/

1. 安装一路next

2. 检查Node.js是否安装成功

   cmd里，查看node版本

   ```
   node -v 
   ```

![image-20211120205950002](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120205950002.png)

此处说明下：新版的Node.js已自带npm，安装Node.js时会一起安装，npm的作用就是对Node.js依赖的包进行管理，也可以理解为用来安装/卸载Node.js需要装的东西

**二、设置nodejs prefix（全局）和cache（缓存）路径**

1、在nodejs安装路径下，新建node_global和node_cache两个文件夹

说明：这里的环境配置主要配置的是npm安装的全局模块所在的路径，以及缓存cache的路径，之所以要配置，是因为以后在执行类似：npm install express [-g] （后面的可选参数-g，g代表global全局安装的意思）的安装语句时，会将安装的模块安装到【C:\Users\用户名\AppData\Roaming\npm】路径中，占C盘空间。
例如：我希望将全模块所在路径和缓存路径放在我node.js安装的文件夹中，则在我安装的文件夹【D:\Develop\nodejs】下创建两个文件夹【node_global】及【node_cache】如下图：

![img](https://upload-images.jianshu.io/upload_images/2267589-51ed23771f3a86e2.png?imageMogr2/auto-orient/strip%7CimageView2/2)

2、设置缓存文件夹

```
npm config set cache "D:\Develop\nodejs\node_cache"
```

​	设置全局模块存放路径

```
npm config set prefix "D:\Develop\nodejs\node_global"
```

**三、环境变量配置**

![img](https://upload-images.jianshu.io/upload_images/2267589-7fd332c898799325.png?imageMogr2/auto-orient/strip%7CimageView2/2)

进入环境变量对话框，在【系统变量】下新建【NODE_PATH】，输入【D:\Develop\nodejs\node_global\node_modules】，将【用户变量】下的【Path】修改为【D:\Develop\nodejs\node_global】

![img](https://upload-images.jianshu.io/upload_images/2267589-ca94af8646fab0b4.png?imageMogr2/auto-orient/strip%7CimageView2/2)![img](https://upload-images.jianshu.io/upload_images/2267589-d469f4d79081582a.png?imageMogr2/auto-orient/strip%7CimageView2/2)





![img](https://upload-images.jianshu.io/upload_images/2267589-f46d2598f3d53958.png?imageMogr2/auto-orient/strip%7CimageView2/2)

![img](https://upload-images.jianshu.io/upload_images/2267589-3c48768a577428f5.png?imageMogr2/auto-orient/strip%7CimageView2/2)







**三、测试**

配置完后，安装个module测试下，我们就安装最常用的express模块，打开cmd窗口，
输入如下命令进行模块的全局安装

```
npm install express -g     # -g是全局安装的意思
```

![image-20211120210709270](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120210709270.png)

（已经装过了，所以上图长这样）

**四、vue3**

官网   https://v3.cn.vuejs.org/guide/introduction.html

多读读官方文档，有大用。

在用 Vue 构建大型应用时推荐使用 npm 安装[[1\]](https://v3.cn.vuejs.org/guide/installation.html#footnote-1) 。npm 能很好地和诸如 [webpack](https://webpack.js.org/) 或 [Rollup](https://rollupjs.org/) 模块打包器配合使用。

```
# 最新稳定版
$ npm install vue@next
```

**五、命令行工具 (CLI)**

Vue 提供了一个[官方的 CLI](https://github.com/vuejs/vue-cli)，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。只需要几分钟的时间就可以运行起来并带有热重载、保存时 lint 校验，以及生产环境可用的构建版本。更多详情可查阅 [Vue CLI 的文档](https://cli.vuejs.org/)。

```
npm install -g @vue/cli
```



**六、检测是否安装成功**

![image-20211120211150759](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211150759.png)

**七、第一个Vue项目**

在桌面上新建文件夹

![image-20211120211246334](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211246334.png)

cmd

![image-20211120211347781](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211347781.png)

```
vue create 项目名
```

![image-20211120211506236](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211506236.png)



![image-20211120211620217](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211620217.png)

![image-20211120211634588](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211634588.png)

![image-20211120211647805](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211647805.png)

至于每个选项什么意思：https://www.cnblogs.com/coober/p/10875647.html







**八、运行Vue项目**

![image-20211120211825880](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120211825880.png)

```
 $ cd hello-vue
 $ npm run serve
```

可以用命令行直接运行，也可以拖进idea，进入 package.json

![image-20211120212052474](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120212052474.png)

tips：加上 --open ，点击运行后自动打开网页

```json
  "scripts": {
    "serve": "vue-cli-service serve --open",
    "build": "vue-cli-service build"
  }
```





**九、按需添加依赖**

![image-20211120212357096](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120212357096.png)

就是上图中的dependencies，需要什么就加入什么，跟pom.xml一样

![image-20211120212507794](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120212507794.png)

```json
  "dependencies": {
    "axios": "^0.24.0",
    "core-js": "^3.6.5",
    "element-plus": "^1.2.0-beta.3",
    "vue": "^3.0.0",
    "vue-axios": "^3.4.0",
    "vue-router": "^4.0.0-0",
    "vuex": "^4.0.0-0"
  }
```

   alt+enter  运行 命令，自动下载所有未install的依赖

```
npm install
```

![image-20211120212644284](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211120212644284.png)

当然也可以在 Terminal 里面写命令

