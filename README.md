Start a RabbitMQ server with docker

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

Run a producer campaign. It will randomly pick a campaign to run.

Use the consumer to subscribe to a campaign 