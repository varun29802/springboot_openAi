package com.varun.openApiTry.service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChatService {

    @Value("${github.models.api.key}")
    private String apiKey;

    private final String endPoint = "<your-api-end-point>";
    private final String model = "<your-model-name>";

    public String getChatCompletion(String prompt){
        ChatCompletionsClient chatCompletionsClient = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endPoint)
                .buildClient();

        List<ChatRequestMessage> requestMessages = Arrays.asList(
                new ChatRequestSystemMessage(""),
                new ChatRequestUserMessage(prompt)
        );

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(requestMessages);
        chatCompletionsOptions.setModel(model);

        ChatCompletions chatCompletions = chatCompletionsClient.complete(chatCompletionsOptions);

        return chatCompletions.getChoice().getMessage().getContent();
    }
}
