package com.demo;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

public class ChatMemoryDemo {

    interface Assistant
    {
        @SystemMessage("You are Teacher's Bot. Answer in one short Sentence")
        String chat(String message);
    }


    public static void main(String[] args) {
        Assistant forgetful = AiServices.create(Assistant.class, Models.chat());
        System.out.println("Forgetful Bot: " + forgetful.chat("My Name is Anuj Vaidya"));
        System.out.println("Forgetful Bot: " + forgetful.chat("What is my name"));
        System.out.println("--------------------------------------");

        Assistant withMemory = AiServices.builder(Assistant.class)
                .chatModel(Models.chat())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        System.out.println("With Memory : " + withMemory.chat("My Name is Anuj Vaidya"));
        System.out.println("With Memory : " + withMemory.chat("What is my name"));
        System.out.println("--------------------------------------");

        System.out.println("--------------------------------------");
        ChatMemory small =MessageWindowChatMemory.withMaxMessages(3);

        Assistant shortMemory = AiServices.builder(Assistant.class)
                .chatModel(Models.chat())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(3))
                .chatMemory(small)
                .build();

        shortMemory.chat("My Name is Anuj Vaidya");
        shortMemory.chat("I am a Java Trainer");
        shortMemory.chat("I teach Spring Boot");
        shortMemory.chat("I Love Driving my BMW");
        System.out.println("Short Memory Bot: " + shortMemory.chat("Do I love Driving"));
        System.out.println("memory stored "+ small.messages().size());


        System.out.println("---------------Token Window Memory-----------------");
        AiServices.builder(Assistant.class)
                .chatModel(Models.chat())

                .chatMemory(
                        TokenWindowChatMemory.builder()
                                .maxTokens(300,
                                        new OpenAiTokenCountEstimator("gpt-4o-mini"))
                                .build())
                .build();
    }
}
