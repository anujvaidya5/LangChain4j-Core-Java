package com.demo;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public class MemoryProviderDemo {

    interface Assistant {
        String chat(@MemoryId String userId, @UserMessage String message);
    }

    public static void main(String[] args) {

        ChatMemoryProvider provider = memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(10)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(Models.chat())
                .chatMemoryProvider(provider)
                .build();
        System.out.println("====================");
        assistant.chat("shramik", "My Favourite programming language is Java");
        assistant.chat("Anuj ", "My Favourite programming language is Python");
        System.out.println("shramik: " + assistant.chat("shramik", "What is my Favourite programming language?"));
        System.out.println("Anuj: " + assistant.chat("Anuj", "What is my Favourite programming language?"));

        System.out.println("Rohit Yadav: " + assistant.chat("Rohit Yadav", "What is my Favourite programming language?"));


    }
}
