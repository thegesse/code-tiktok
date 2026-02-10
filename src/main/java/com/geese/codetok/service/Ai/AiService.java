package com.geese.codetok.service.Ai;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final Client client;
    private final String model;

    public AiService(
            @Value("${spring.ai.google.genai.api-key}") String apiKey,
            @Value("${spring.ai.google.genai.chat.options.model}") String model) {

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
        this.model = model;
    }

    //short format code problems for scrolling
    private String getProblem(String level, String topic) {
        String prompt = String.format(
                "Generate a short Java code snippet for a %s level programmer. " +
                        "The snippet must contain exactly one error that would cause the program to fail to run and send out a error message. The code snippet must not contain any comments that would indicate an answer but only have a few comments pertaining on how the code works " +
                        "Topic: %s. " +
                        "Return the response in this format: CODE: [code with error] ERROR_MESSAGE: [error message] ANSWER: [what the code was originally meant to print out if ran correctly]", level, topic);

        // This creates the empty config object the method is looking for
        com.google.genai.types.GenerateContentConfig config =
                com.google.genai.types.GenerateContentConfig.builder().build();

        try {
            // Pass all 3 arguments: model, prompt, and the config
            GenerateContentResponse response = client.models.generateContent(this.model, prompt, config);
            return response.text();
        } catch (Exception e) {
            System.err.println("AI Request Failed: " + e.getMessage());
            return "CODE: System.out.println(\"Error\"); ANSWER: Error";
        }
    }

    public String beginnerCodeProblem(String topic) {return getProblem("beginner", topic);}
    public String intermediateCodeProblem(String topic) {return getProblem("intermediate", topic);}
    public String expertCodeProblem(String topic) {return getProblem("expert", topic);}

    // reminder to implement a longer format for code puzzles feature in the future
}