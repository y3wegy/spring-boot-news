https://www.jianshu.com/p/9103c9c7e128
服务端处理消息的场景：

+ 公告就是只要订阅了此路径的的用户都能收到，我们使用@SendTo 注解实现，如果不使用注解指定，会默认交给broker进行处理，例如@MessageMapping("/demo1/twoWays") 这种，就会拼接代理域+路径
相当于配置了@SendTo("/topicTest/twoWays")，也可以使用SimpMessagingTemplate.convertAndSend
+ 私信就是指定人员才能收到，可以用@SendToUser 注解或者SimpMessagingTemplate 模板类(框架提供)的convertAndSendToUser进行处理

+ @SendToUser 多用于资源的请求，如果我只是想简单的用websocket向服务器请求资源而已，然后服务器你就把资源给我就行了，别的用户就不用你广播推送了，简单点，就是我请求，你就推送给我
+ SimpMessagingTemplate.convertAndSendToUser 可以用户发送指定的人员
+ 使用指定人员发送的时候，前缀必须为配置的setUserDestinationPrefix 配置的“/user”，在spring 框架内部以"/user" 为前缀的消息将会通过 UserDestinationMessageHandler 进行处理，而不是 AnnotationMethodMessageHandler 或 SimpleBrokerMessageHandler or StompBrokerRelayMessageHandler。UserDestinationMessageHandler 的主要任务： 是 将用户消息重新路由到 某个用户独有的目的地上。 在处理订阅的时候，它会将目标地址中的 "/user" 前缀去掉，并基于用户 的会话添加一个后缀。如，对 "/user/userTest/notifications" 的订阅最后可能路由到 名为 "/userTest/notifacations-user65a4sdfa" 目的地上