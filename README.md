# Spring-Hibernate-Jpa-Example

**All tasks including "bonus tasks" are performed. RabbitMQ was used instead of ActiveMQ, because i'm more familiar with it.**

Please, use **docker.compose** for postgresql and rabbitmq local instances creation, before testing the app.

You can find **swagger** thru link -> http://localhost:8088/swagger-ui.htm

**RabbitMq** can be found on -> http://localhost:15672/#/
RabbitMQ is used for sending messages, when one of the methods in API is called. Rabbit listener publish those messages
 in log and also send them to the **gRPC**, which also publush them in log(in gRPC server log).

**PLEASE, BE AWARE:**
**1)** Before launching SpringHibernateJpaRawApplication (which is SpringBootApp), start gRPC server GrpcServer.

**2)** Also, pay attention to that fact, that the messages received by gRPC server will be reflected **only** in gRPC log.  
