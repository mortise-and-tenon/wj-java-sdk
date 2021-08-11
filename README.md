## 腾讯问卷java-sdk

[腾讯问卷](https://wj.qq.com/docs/openapi) java-sdk，欢迎试用

### 快速开始
1. 首先需要申请企业版腾讯问卷
2. 获取腾讯问卷颁发的appId和secret
3. 引入jar包
- `maven` 依赖：
```xml
<dependency>
  <groupId>fun.mortnon</groupId>
  <artifactId>wj-java-sdk</artifactId>
  <version>${最新发布版本}</version>
</dependency>
```
4. 代码如下
```java
WjStorageConfig wjStorageConfig = new WjDefaultStorageConfigImpl();
wjService = new WjServiceImpl(wjStorageConfig, APP_ID, SECRET);
    
// 获取accessToken，测试用，项目中不需要手动获取token
AccessToken accessToken = wjService.accessToken(APP_ID, SECRET, null);

// 获取问卷列表    
WjManageService wjManageService = wjService.getWjManageService();
WjPage<Survey> surveyWjPage = wjManageService.listSurvey(USER_ID, null, 1, 20);
    
// 其他接口可自行查看
```