package com.geese.codetok.service.Ai;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PromptTopics {
    private final Random random = new Random();
    private final List<String> beginnerTopics = List.of(
            "What is programming",
            "Variables and data types",
            "Operators",
            "Input and output",
            "Conditional statements (if/else)",
            "Loops (for, while)",
            "Functions / methods",
            "Basic arrays and lists",
            "Strings",
            "Basic debugging",
            "Intro to Git and GitHub"
    );

    private final List<String> intermediateTopics = List.of(
            "Object-oriented programming (OOP)",
            "Classes and objects",
            "Inheritance and polymorphism",
            "Encapsulation and abstraction",
            "Exception handling",
            "File handling",
            "Data structures (stack, queue, linked list)",
            "Sorting and searching algorithms",
            "Big-O time complexity",
            "APIs and HTTP basics",
            "Unit testing",
            "Refactoring and clean code"
    );

    private final List<String> expertTopics = List.of(
            "Advanced data structures (trees, graphs, heaps)",
            "Dynamic programming",
            "Concurrency and multithreading",
            "Memory management",
            "System design",
            "Distributed systems",
            "Microservices architecture",
            "Performance optimization",
            "Security best practices",
            "Design patterns",
            "CI/CD pipelines",
            "Scalable and fault-tolerant systems"
    );

    public String getRandomTopicBeginner() {
        return beginnerTopics.get(random.nextInt(beginnerTopics.size()));
    }
    public String getRandomTopicIntermediate() {
        return intermediateTopics.get(random.nextInt(intermediateTopics.size()));
    }
    public String getRandomTopicExpert() {
        return expertTopics.get(random.nextInt(expertTopics.size()));
    }
}
