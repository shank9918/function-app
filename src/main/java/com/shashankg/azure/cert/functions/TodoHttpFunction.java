package com.shashankg.azure.cert.functions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Azure Functions with HTTP Trigger.
 */
public class TodoHttpFunction {
	/**
	 * This function listens at endpoint "/api/TodoHttpFunction". Two ways to invoke it using "curl" command in bash:
	 * 1. curl -d "HTTP Body" {your host}/api/TodoHttpFunction
	 * 2. curl {your host}/api/TodoHttpFunction?name=HTTP%20Query
	 */
	@FunctionName("TodoHttpFunction")
	public HttpResponseMessage run(
			@HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) {
		Logger logger = context.getLogger();
		logger.info("Request Headers:" + request.getHeaders());
		logger.info("Size: " + request.getHeaders().keySet().size());
		request.getHeaders().keySet().forEach(key -> {
			logger.info(key + " : " + request.getHeaders().get(key));
		});
		context.getLogger().info("Java HTTP trigger processed a request.");
		Optional<String> requestBody = request.getBody();

		if (!requestBody.isPresent()) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a todo entity in request body").build();
		} else {
			String requestBodyString = requestBody.get();
			return request.createResponseBuilder(HttpStatus.OK).body("Todo Details, " + requestBodyString).build();
		}
	}
}
