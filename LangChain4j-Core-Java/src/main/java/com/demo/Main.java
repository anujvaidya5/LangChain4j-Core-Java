package com.demo;

import dev.langchain4j.model.openai.OpenAiChatModel;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args)
    {
        System.out.println("Hi , we will start learning langCjain4j");

        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        String response = chatModel.chat("Hi , Tell me about LangChain4j in short .");

        System.out.println(response);
    }
}
