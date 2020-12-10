# Spring-Hibernate-Jpa-Example

Please, use **docker.compose** for postgresql and rabbitmq local instances creation, before testing the app.

You can find **swagger** thru link -> http://localhost:8088/swagger-ui.htm

**RabbitMq** can be found on -> http://localhost:15672/#/
RabbitMQ is used for sending messages, shen one of the methods in API is called. Rabbit listener publish those messages
 in log and also send them to the **gRPC**, which also publush them in log.

**PLEASE, BE AWARE:**
**1)** There are two roles - USER(user, password) and ADMIN(admin, password).
Only two API controllers avaliable for USER role - StudentController and TeacherController
All three are avaliable for admin - StudentController, TeacherController and StudentToTeacherRelationController

**2)** Before launching SpringHibernateJpaRawApplication (which is SpringBootApp), start gRPC server GrpcServer.
Also, pay attention to that fact, that the messages received by gRPC server will be reflected in gRPC log.  
