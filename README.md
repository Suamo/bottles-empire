## Configuration server
* Creating custom configuration server
    * dependency: spring-cloud-config-server
    * property: spring.cloud.config.server.git.uri
    * property: spring.cloud.config.server.git.searchPaths
    * @EnableConfigServer
* Usage of configuration server by client app
    * dependency: spring-cloud-starter-config
    * property: spring.cloud.config.uri
    * property: spring.cloud.config.username
    * property: spring.cloud.config.password
    * property: encrypt.key
* Refreshing configuration
    * dependency: spring-boot-starter-actuator
    * property: management.endpoints.web.exposure.include=*
    * @RefreshScope
* Securing configuration server
    * install Java Cryptography Extension
    * dependency: spring-boot-starter-security
    * property: spring.security.user.name
    * property: spring.security.user.password
    * property: encrypt.key

## Service discovery
* Creating Eureka server
    * dependency: spring-cloud-starter-netflix-eureka-server
    * @EnableEurekaServer
* Usage of Eureka server by client app
    * dependency: spring-cloud-starter-netflix-eureka-client

## Securing microservices (Single Sign-On)
* External authentication (GitHub
* OAuth2 authorization
    * dependency: spring-cloud-starter-security
    * dependency: spring-cloud-starter-oauth2
    * properties: a lot
    * @EnableOAuth2Sso
* Propagating token to other services
    * using @Bean OAuth2RestTemplate on sender side
    * using @EnableResourceServer on receiver side
    * property security.oauth2.resource.user-info-uri on receiver side
    
## Services coordination
* Synchronous (REST)
* Asynchronous (RabbitMQ)
* Latency and dependency tracing (Sleuth + Zipkin)
    * dependency: spring-cloud-starter-zipkin

## Load balancing:
* Client load balancing (Ribbon)
    * @Bean @LoadBalanced
* Stream load balancing
    * property: spring.cloud.stream.bindings.input.group
* Proxy (Zuul)

## Serverless services
* Task Launcher
    * dependency: spring-cloud-starter-task
    * @EnableTaskLauncher
    * @Bean LocalTaskLauncher
* Task Launching request
    * dependency: spring-cloud-starter-task
    * prepare a service artifact
* Spring Cloud Task
    * dependency: spring-cloud-starter-task
    * dependency: spring-boot-starter-jdbc
    * dependency: mysql-connector-java
    * @EnableTask
    * @Bean CommandLineRunner

## Circuit Breaker
* Hystrix
    * dependency: spring-cloud-starter-netflix-hystrix
    * @EnableCircuitBreaker
    * @HystrixCommand
* Hystrix dashboard
    * dependency: spring-cloud-starter-netflix-hystrix-dashboard
    * @EnableHystrixDashboard
    * property: management.endpoints.web.exposure.include
* Turbine