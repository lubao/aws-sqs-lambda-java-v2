package example;

import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;


public class Hello implements RequestHandler<Request, Response> {

    private static final String QUEUE_NAME = "test";
    final SqsClient sqsClient = SqsClient.builder().build();



    public Response handleRequest(Request request, Context context) {
        System.out.println("\nGet queue url");
        // snippet-start:[sqs.java2.sqs_example.get_queue]
        GetQueueUrlResponse getQueueUrlResponse =
            sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName(QUEUE_NAME).build());
        String queueUrl = getQueueUrlResponse.queueUrl();
        System.out.println(queueUrl);
        System.out.println("Hello");
        SendMessageRequest send_msg_request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody("hello world for Java SDK2")
                .delaySeconds(5)
                .build();
        sqsClient.sendMessage(send_msg_request);

        String greetingString = String.format("Hello %s %s.", request.firstName, request.lastName);
        return new Response(greetingString);
    }
}
