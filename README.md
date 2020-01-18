# akali-oauth2-server
基于spring oauth2的鉴权授权服务器  
项目代号：akali  
项目代号来源：LOL的阿卡丽的神秘商店，提供一些神秘的工具用于降低开发难度  

项目运行  
先运行数据库文件，生成默认的数据  
默认的用户名和密码：user/123456  
默认的客户端和密码：oauth2/oauth2  
### 授权码模式 
浏览器打开连接  
http://localhost:8888/oauth/authorize?response_type=code&client_id=oauth2&redirect_uri=https://barryquan.github.io&state=123   
要输入的用户名和密码就是默认的用户名和密码  
然后授权会跳转到重定向的url，获取code=xxx  
调用token获取接口  
http://localhost:8888/oauth/token?grant_type=authorization_code&code=xxx&redirect_uri=https://barryquan.github.io  
返回对应的token请求完成  

### 密码模式 
调用接口  
http://localhost:8888/oauth/token?username=user&password=123456&grant_type=password  


#### 注意：调用/oauth/tokend都需要进行Basic Auth校验，校验的用户名和密码为默认的客户端和密码，建议/oauth/tokend接口使用接口测试工具进行测试  

参考：[spring security 官方文档](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)