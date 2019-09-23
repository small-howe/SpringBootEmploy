# 技术点:
### 一:代码自动生成
```
使用:1.引入依赖
     2.配置文件文件 generatorConfig.xml
     3.添加Maven运行命令:-Dmybatis.generator.overwrite=true mybatis-generator:generate -e(执行后有覆盖作用)
     4.执行成功后就会 生成entity mapper mapper.xml 
```
### Swagger 技术应用:
###[Swagger技术](https://swagger.io/)
### 在项目中使用Swagger:SpringBOX
 * swagger2
 * ui
### 集成Swagger:
 ```java
 
 //1.配置编写 Config
 /**
  * Swagger 配置
  * @Configuration 加入至配置
  */
 @Configuration
 @EnableSwagger2 //开启Swagger2
 public class SwaggerConfig {
 }
 ```
 * 测试: http://localhost:8080/swagger-ui.html
![](http://small-howe.cn-bj.ufileos.com/9793334d-ea6e-4c57-805a-09331e113655.png?UCloudPublicKey=TOKEN_7728f428-03f0-4a5f-b3fd-e5632771d02e&Signature=OQ0CL4EDko2GXF5q1uWZVzt1P%2F8%3D&Expires=1884601139)

### 配置Swagger:
```java
/**
 * Swagger 配置
 * @Configuration 加入至配置
 */
@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {
   //Swagger 的Bean 实例Docket
    // 配置了Swagger的Bean实例
    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }



    //配置Wagger 信息 = apiInfo();

    public ApiInfo apiInfo(){
        Contact contact = new Contact("唐维豪", "urn:tos", "2678691035@qq.com");
        return new ApiInfo(
                "Howe Swagger API 文档",  //标题  (重点写)
                "再小的帆也能远航", // 描述 (重点写)
                "1.0",         // 版本
                "urn:tos",
                contact,              // 作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}

```
### 配置Swagger扫描接口:
```java
/**
 * Swagger 配置
 * @Configuration 加入至配置
 */
@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {

    // 配置了Swagger的Bean实例
    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable(false)是否启动Swagger  默认(true)
                .enable(false)
                .select()
                // RequestHandlerSelectors配置要扫描的接口方式
                //basePackage :指定要扫描的包
                //any():扫描全部
                //none():都不扫描
                //withMethodAnnotation():扫描方法上的注解
                //withClassAnnotation()):扫描类上的注解 参数:是一个注解的反射对象
                .apis(RequestHandlerSelectors.basePackage("com.tangwh.controller"))
                // paths():过滤什么路径
             // .paths(PathSelectors.ant("/tangwh/**"))
                .build();
    }


    //配置Wagger 信息 = apiInfo();

    public ApiInfo apiInfo(){
        Contact contact = new Contact("唐维豪", "urn:tos", "2678691035@qq.com");
        return new ApiInfo(
                "Howe Swagger API 文档",  //标题  (重点写)
                "再小的帆也能远航", // 描述 (重点写)
                "1.0",         // 版本
                "urn:tos",
                contact,              // 作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
```
### 问题:我只希望我的Swagger在生产环境使用,在发布的时候不使用
 * 判断是不是生产环境 flag =false
 * enable(flag)
 ```java
/**
 * Swagger 配置
 * @Configuration 加入至配置
 */
@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {

    // 配置了Swagger的Bean实例

    @Bean
    public Docket docket(Environment environment){

       // 问题:我只希望我的Swagger在生产环境使用,在发布的时候不使用
        // 设置要显示的Swagger的 环境
        Profiles profiles = Profiles.of("dev");
        //1.获取项目环境 通过environment.acceptsProfiles 判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable(false)是否启动Swagger  默认(true)
                .enable(flag)
                .select()
                // RequestHandlerSelectors配置要扫描的接口方式
                //basePackage :指定要扫描的包
                //any():扫描全部
                //none():都不扫描
                //withMethodAnnotation():扫描方法上的注解
                //withClassAnnotation()):扫描类上的注解 参数:是一个注解的反射对象
                .apis(RequestHandlerSelectors.basePackage("com.tangwh.controller"))
                // paths():过滤什么路径
                .paths(PathSelectors.ant("/tangwh/**"))
                .build();
    }


    //配置Wagger 信息 = apiInfo();

    public ApiInfo apiInfo(){
        Contact contact = new Contact("唐维豪", "urn:tos", "2678691035@qq.com");
        return new ApiInfo(
                "Howe Swagger API 文档",  //标题  (重点写)
                "再小的帆也能远航", // 描述 (重点写)
                "1.0",         // 版本
                "urn:tos",
                contact,              // 作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
```
### 配置Api文档分组:
```
.groupName("唐维豪") // 分组
```
* 配置多个分组?:配置多个Docket是实例即可
```java
 public class SwaggerConfig {
   // 组1
     @Bean
     public Docket docket1(){
         return new Docket(DocumentationType.SWAGGER_2).groupName("A");
     }
     // 组2
     @Bean
     public Docket docket2(){
         return new Docket(DocumentationType.SWAGGER_2).groupName("B");
     }
     // 组3
     @Bean
     public Docket docket3(){
         return new Docket(DocumentationType.SWAGGER_2).groupName("C");
     }
}
```
### 实体类配置:
```java

// @Api("注释") Swagger 中的实体注释
@ApiModel("员工实体类")  // Swagger 中的实体注释
public class Emp {
    @ApiModelProperty("员工编号") // Swagger 中的属性注释
    private Integer id;
    @ApiModelProperty("员工姓名")
    private String emmpName;
    @ApiModelProperty("员工性别")
    private String gender;
    @ApiModelProperty("员工邮箱")
    private String email;
    @ApiModelProperty("部门编号")
    private Integer dId;
    }
```
### [swagger2常用注解说明](https://blog.csdn.net/u014231523/article/details/76522486)