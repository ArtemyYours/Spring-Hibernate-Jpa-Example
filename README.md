# Spring-Hibernate-Jpa-Example

Please< use docker.compose for postgresql and rabbitmq local instances creation, before testing the app.

You can find swagger thru link -> http://localhost:8088/swagger-ui.htm

RabbitMq can be found on -> http://localhost:15672/#/

PLEASE, BE AWARE:
There are two roles - USER(user, password) and ADMIN(admin, password).
Only two API controllers avaliable for USER role - StudentController and TeacherController
All three are avaliable for admin - StudentController, TeacherController and StudentToTeacherRelationController
