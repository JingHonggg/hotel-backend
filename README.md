# hotelManage

<p align="center">
   hotel，一个基于springboot+Vue的前后端分离酒店管理系统
  	<br>
    <img src="https://img.shields.io/badge/jdk-1.8+-brightgreen.svg" ></img><img src="https://img.shields.io/badge/springboot-2.3.1-brightgreen.svg" ></img><img src="https://img.shields.io/badge/mybatisplus-3.3.2-brightgreen.svg" ></img><img src="https://img.shields.io/badge/swagger-2.9.2-brightgreen.svg" ></img><img src="https://img.shields.io/badge/vue-2.6.10-brightgreen.svg" ></img><img src="https://img.shields.io/badge/elementui-2.8.2-brightgreen.svg" ></img>
</p>


## 1.前言

## 2.开始步骤


### 2.1 后端运行

- 将hotel.sql导入数据库  （存在resource目录下的）
- 修改application中的数据库配置(用户名，密码，**端口号**)
- 直接运行即可

> 数据库版本请用  5.X.X 版本
>
> 后端运行成功后可直接访问swagger文档：http://localhost:8088/hotel-api/swagger-ui.html
>

### 2.2 前端运行

- 安装项目依赖

 ```bash
  npm install --registry=https://registry.npm.taobao.org
 ```

- 开启服务器，浏览器访问 http://localhost:8080

```
npm run dev
```

## 3. 采用技术

|      技术      |           说明            |                             官网                             |
| :------------: | :-----------------------: | :----------------------------------------------------------: |
|   SpringBoot   |          MVC框架          | [ https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
|  MyBatis-Plus  |          ORM框架          |                   https://mp.baomidou.com/                   |
|   Swagger-UI   |       接口文档生成工具        | [ https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) |
|      JWT       |        JWT登录支持        |                 https://github.com/jwtk/jjwt                 |
|     SLF4J      |         日志框架          |                    http://www.slf4j.org/                     |
|     Lombok     |     简化对象封装工具      | [ https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok) |
|      Vue-router       |                路由框架                 |                  https://router.vuejs.org/                   |
|         Vuex          |            全局状态管理框架             |                   https://vuex.vuejs.org/                    |
|        Element        |               前端ui框架                |    [ https://element.eleme.io](https://element.eleme.io/)    |
|         Axios         |              前端HTTP框架               | [ https://github.com/axios/axios](https://github.com/axios/axios) |

## 4.开发工具

|     工具     |       说明        |                             官网                             |
| :----------: | :---------------: | :----------------------------------------------------------: |
|     IDEA     |    Java开发IDE    |           https://www.jetbrains.com/idea/download            |
|   WebStorm   |    前端开发IDE    |             https://www.jetbrains.com/webstorm/              |
|   Navicat   |  数据库连接工具   |               https://www.navicat.com.cn/              |

