package com.demo;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.time.Duration;
import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args)
    {

        System.out.println("Hi , Welcome to the Teacher's chatbot .");

        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .temperature(0.8)
                .timeout(Duration.ofSeconds(30))
                .build();

        MessageWindowChatMemory memory = MessageWindowChatMemory.withMaxMessages(20);

        memory.add(
                SystemMessage.from(  "You are Teacher's Bot, an assistant for a Java and Spring Boot training company. " +
                        "Keep every answer under three sentences. " +
                        "If you do not know something, say so.")
        );

        Scanner scanner = new Scanner(System.in);
        System.out.println("Teacher's Bot is ready. Chat Now. Also Type   'exit' to quit");

        while (true){
            System.out.println("You : ");
            String input = scanner.nextLine();

            if("exit".equalsIgnoreCase(input.trim()))
            {
                break;
            }

            memory.add(UserMessage.from(input));
            AiMessage aiMessage = chatModel.chat(memory.messages()).aiMessage();

            System.out.println("Bot : "+ aiMessage.text() + "\n");
            memory.add(aiMessage);
        }

        scanner.close();
        System.out.println("Bye Have a nice day");

    }
}
