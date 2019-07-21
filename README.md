# spring cloud
### 说明
1. 配置中项目采用spring-cloud-config从git摘取配置
2. 

|模块|实现|访问|说明|
|-|-|-|-|
|配置中心|spring-cloud-config|[9400](http://localhost:9400/)|从git中获取配置<br>@EnableConfigServer, @EnableDiscoveryClient|
|服务注册与发现|spring-netflix-eureka|[9100](http://localhost:9100/)|@EnableEurekaServer|
|服务提供|springboot|[9200](http://localhost:9200/hello?name=zjy)|1. 使用bootstrap.yml配置配置服务获取相关配置<br>2. 指定zipkin和sleuth进行服务调用跟踪<br>3. @EnableEurekaClient, @EnableDiscoveryClient,@EnableCircuitBreaker|
|服务消费|spring-cloud-gateway|[9000](http://localhost:9000/spring-cloud-producer/hello?name=zjy&token=1)|1. 使用fegin。<br>2. 使用bootstrap.yml配置配置服务获取相关配置<br>3. 指定zipkin和sleuth进行服务调用跟踪<br>4. 通过@RefreshScope来刷新获取spring-cloud-config中改变的配置<br>5. 指定zipkin和sleuth进行服务调用跟踪<br>6. @EnableEurekaClient, @EnableFeignClients, @EnableDiscoveryClient,@EnableCircuitBreaker|
|服务监控|turbine，hystrixdashboard|[9500](http://127.0.0.1:9500/hystrix/)|1. 通过eureka拉取服务信息<br>2. 集群监控(输入 http://127.0.0.1:9500/turbine.stream)<br>2. 监控多个服务（turbine.app-config: spring-cloud-producer,hystrix）<br>3. 单个服务监控(输入 http://localhost:9200/actuator/hystrix.stream)<br>4. 监控服务时要访问服务才可以在监控中显示。<br>5. 被监控的服务需要添加ServletRegistrationBean映射。<br>7. 添加@HystrixCommand注解的服务才能被监控<br>8. @EnableTurbine, @EnableHystrixDashboard, @EnableDiscoveryClient|
|服务追踪|zipkin，sleuth|[9411](http://localhost:9411/zipkin/)|1. 运行服务：java -jar zipkin-server-2.12.9-exec.jar<br>2. 引入sleuth和zipkin的starter即可|