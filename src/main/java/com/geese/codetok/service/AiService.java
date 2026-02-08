package com.geese.codetok.service;

import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    @Value("${gemini.api.key}")
    private String apiKey;
}
