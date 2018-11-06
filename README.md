# javaJMS
1.在官网[http://activemq.apache.org/](http://activemq.apache.org/)下载ActiveMQ，根据自己系统的型号启动ActiveMQ。(我的是apache-activemq-5.9.0\bin\win64中，双击运行activemq批处理文件。  
2.默认访问[http://localhost:8161/admin/](http://localhost:8161/admin/) 可以进入ActiveMQ管理页面
![Image text](https://github.com/doraemon4/javaJMS/blob/master/ActiveMQ.png)
3.开启消息生产者，会在监控页面看到产生的消息总数，没有消费和已经消费的消息。  
4.开启消息消费者，会在监控页面看到消费者的数量，消息消费了多少。
![Image text](https://github.com/doraemon4/javaJMS/blob/master/consumer01.png)  
5.发布订阅和队列模式的不同是：发布订阅必须先有消费者，再开启生产者，每个消费者收到的消息都是一样的。
![Image text](https://github.com/doraemon4/javaJMS/blob/master/topic.png)  
