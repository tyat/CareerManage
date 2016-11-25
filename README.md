# CareerManage
这是一个SpringMVC设计的就业管理系统。完成对学生就业相关功能模块的开发。

- SpringMVC
- Hibernate
- 阿里数据源
- MySQL5.6

## 工程结构

#### other/

&nbsp;内部包含此工程项目的所有技术文档与数据库及数据源配置信息。其中Document包&nbsp;含了系统的相关文档，Database包含了MySQL数据库脚本以及数据源配置信息。<br/>

#### src/main/java/

&nbsp;此目录中包含项目中所有的Java源码。<br/>
&nbsp;&nbsp;1.src/main/java/com/control:&nbsp;控制层;<br/>
&nbsp;&nbsp;2.src/main/java/com/pojo:&nbsp;持久化层;<br/>
&nbsp;&nbsp;3.src/main/java/com/ResObj:&nbsp;自定义返回对象;<br/>
&nbsp;&nbsp;4.src/main/java/com/service:&nbsp;核心业务逻辑层;<br/>
&nbsp;&nbsp;5.src/main/java/com/tools:&nbsp;工具类。<br/>

#### src/main/webapp/
&nbsp;此目录为视图层前台网页，包含项目中JSP页面。<br/>


#### Git运作流程
&nbsp;详见：other目录下的图片

## 快速开始
1. 安装Maven
2. Maven编译
3. 在MySQL数据库创建career数据库,将other/db/career.sql数据库脚本文件导入数据库中,创建相关表.
4. 将druid.jar,mysql数据库驱动两个包,放入tomcat中lib目录中。
5. 配置Tomcat的数据源,即context.xml文件,示例配置信息,可见other/db/数据源配置信息.txt
6. 重启tomcat

## 使用
地址:http://localhost:8080/CareerManage/ <br/>

## 开发者

- TianYu@USTSD 


## 版本

V 1.0


## 修改日志
- 2016-11-20:
- [x] 对项目的登录页面做了修改。


## 参考资源
以下是在编写案例中收集的资源,对深入理解与运用有帮助

- [使用IntelliJ IDEA开发SpringMVC网站](http://my.oschina.net/gaussik/blog/385697)


------

