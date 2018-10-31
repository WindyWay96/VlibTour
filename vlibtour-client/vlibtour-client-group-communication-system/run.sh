#!/bin/bash

MODULE_VERSION=0.1-SNAPSHOT
RABBITMQ_CLIENT_VERSION=5.4.1


if [[ -f ./target/step5-${MODULE_VERSION}.jar ]]
then
    export JARS=./target/step5-${MODULE_VERSION}.jar
else
    echo Archive file ./target/step5-${MODULE_VERSION}.jar missing
    echo Run maven install to generate it
fi

if [[ -f ${HOME}/.m2/repository/com/rabbitmq/amqp-client/${RABBITMQ_CLIENT_VERSION}/amqp-client-${RABBITMQ_CLIENT_VERSION}.jar ]]
then
    export JARS=${HOME}/.m2/repository/com/rabbitmq/amqp-client/${RABBITMQ_CLIENT_VERSION}/amqp-client-${RABBITMQ_CLIENT_VERSION}.jar:${JARS}
else
    echo Archive file ${HOME}/.m2/repository/com/rabbitmq/amqp-client/${RABBITMQ_CLIENT_VERSION}/amqp-client-${RABBITMQ_CLIENT_VERSION}.jar missing
    echo Run maven install to install it on your local maven repository
fi

if [[ -f ${HOME}/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar ]]
then
    export JARS=${HOME}/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:${JARS}
else
    echo Archive file ${HOME}/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar missing
    echo Run maven install to install it on your local maven repository
fi

if [[ -f ${HOME}/.m2/repository/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar ]]
then
    export JARS=${HOME}/.m2/repository/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar:${JARS}
else
    echo Archive file ${HOME}/.m2/repository/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar missing
    echo Run maven install to install it on your local maven repository
fi

rabbitmqctl stop
rabbitmq-server -detached
rabbitmqctl stop_app
rabbitmqctl reset
rabbitmqctl start_app

java -cp ${JARS} ${CONSUMER} 'hello' &
TOREMOVE1=$!

sleep 2
rabbitmqctl list_queues name durable auto_delete
rabbitmqctl list_exchanges
rabbitmqctl list_bindings