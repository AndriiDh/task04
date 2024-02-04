package com.task04;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.events.SnsEventSource;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;

@LambdaHandler(lambdaName = "sns_handler",
	roleName = "sns_handler-role"
)
@DependsOn(name = "lambda_topic", resourceType = ResourceType.SNS_TOPIC)
@SnsEventSource(targetTopic = "lambda_topic")
public class SnsHandler implements RequestHandler<Object, String> {

	public String handleRequest(Object request, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("SNS event: " + request);
		return "SUCCESS";
	}
}
