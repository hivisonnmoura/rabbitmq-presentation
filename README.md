# RabbitMQ-Presentation



### Rabbit MQ on docker

To run rabbit MQ, we can run the next docker command:
```
$ docker run -d --hostname localhost --name local-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```
Before the first start, we can stop and start the docker with the command `docker stop local-rabbit` and `docker start local-rabbit`start, we can stop and start the docker with the command docker stop local-rabbit and docker start local-rabbit