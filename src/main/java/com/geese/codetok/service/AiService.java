package com.geese.codetok.service;

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
                        "The snippet must contain exactly one logical or syntax error. " +
                        "Topic: %s. " +
                        "After the code, specify the exact error message.", level, topic);
        GenerateContentResponse response = client.models.generateContent(model, prompt, null);
        return response.text();
    }

    public String beginnerCodeProblem(String topic) {return getProblem("beginner", topic);}
    public String intermediateCodeProblem(String topic) {return getProblem("intermediate", topic);}
    public String expertCodeProblem(String topic) {return getProblem("expert", topic);}

    // reminder to implement a longer format for code puzzles feature in the future
}