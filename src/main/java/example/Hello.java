package example;

import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

/*
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.ClientConfiguration;

import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;

import java.util.Date;
import java.util.List;
*/

public class Hello implements RequestHandler<Request, Response> {

    //private static final String QUEUE_NAME = "test";
    final SqsClient sqs = SqsClient.builder().build();
    //final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();//.standard().
        //withClientConfiguration(new ClientConfiguration().withMaxConnections(1)).build();//;.defaultClient();

    public Response handleRequest(Request request, Context context) {
        System.out.println("Hello");
        SendMessageRequest send_msg_request = SendMessageRequest.builder()
                .queueUrl("https://sqs.us-east-2.amazonaws.com/******/test")
                .messageBody("hello world for Java SDK2")
                .delaySeconds(5)
                .build();
        sqs.sendMessage(send_msg_request);
        //String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        //SendMessageRequest send_msg_request = new SendMessageRequest()
        //        .withQueueUrl("https://sqs.us-east-2.amazonaws.com/697732210755/test")
        //        .withMessageBody("hello world Java SDK")
        //        .withDelaySeconds(5);
        //sqs.sendMessage(send_msg_request);

        String greetingString = String.format("Hello %s %s.", request.firstName, request.lastName);
        return new Response(greetingString);
    }
}
