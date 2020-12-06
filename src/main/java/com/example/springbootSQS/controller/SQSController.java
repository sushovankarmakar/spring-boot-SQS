package com.example.springbootSQS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {

    Logger LOGGER = LoggerFactory.getLogger(SQSController.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    /* AWS SDK auto-configures everything in our spring boot application.
    * We are not running our application in the EC2 instance.
    * So We need to disable the EC2 meta data information over here.
    * For that, we need to use either
    * @SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
    * or cloud:
    *       aws:
    *           stack:
    *               auto: false
    * */

    // https://stackoverflow.com/questions/16449126/kafka-or-sns-or-something-else

    @GetMapping(value = "/put/{message}")
    public void putMessageToQueue(@PathVariable(value = "message") String message) {
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener(value = "sqs-queue")
    public void loadMessageFromQueue(String message) {
        LOGGER.info("Message from SQS queue : {}" , message);
    }
}
