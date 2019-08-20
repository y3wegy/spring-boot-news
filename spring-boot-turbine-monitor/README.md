turbine语法：
+ `turbine.aggregator.clusterConfig`:指定聚合哪些集群，多个使用","分割，默认为default。可使用http://.../turbine.stream?cluster={clusterConfig之一} 访问
+ `turbine.appConfig`:配置Eureka中的serviceId列表，表明监控哪些服务
+ `turbine.clusterNameExpression`有几种情形：
   + `clusterNameExpression指定集群名称`，默认表达式appName；此时：`turbine.aggregator.clusterConfig`需要配置想要监控的应用名称
   + 当`clusterNameExpression`: default时，`turbine.aggregator.clusterConfig`可以不写，因为默认就是default
   + 当`clusterNameExpression`: metadata['cluster']时，假设想要监控的应用配置了`eureka.instance.metadata-map.cluster`: ABC，则需要配置，同时`turbine.aggregator.clusterConfig`: ABC
---
完成以上步骤后，需要在启动类中添加注解@EnableDiscoveryClient 和 @EnableTurbine，然后启动项目即可。

浏览器地址栏依旧输入http://localhost:9201/hystrix，这是ribbon和feign与hystrix dashboard整合后访问的地址 (web地址)，然后在打开的页面输入栏中填入：http://localhost:9065/turbine.stream ，然后点击Monitor Stream，在已经分别调用过对应方法（ http://localhost:9063/testhttp://localhost:9064/test ）