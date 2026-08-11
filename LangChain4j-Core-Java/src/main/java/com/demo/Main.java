package com.demo;

import com.sun.tools.jconsole.JConsoleContext;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

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

//        String aiResponse = chatModel.chat("Hi , Tell me about LangChain4j in short .");
//
//        System.out.println(aiResponse);
//
//        SystemMessage systemMessage = SystemMessage.from(
//                "You are a Java trainer at Telusko. Answer in short bullet points. " +
//                        "Never answer questions that are not about programming."
//        );
//
//        UserMessage userMessage = UserMessage.from("HI , Tell me why we need interfaces in Java ?");
//
//        ChatResponse response = chatModel.chat(systemMessage, userMessage);
//        AiMessage reply = response.aiMessage();
//        System.out.println("Answer: "+ reply.text());
//        System.out.println("Model used : "+ response.modelName());
//        System.out.println("Total In/Out Tokens used : "+ response.tokenUsage().totalTokenCount());
//        System.out.println("Total In Tokens used : "+ response.tokenUsage().inputTokenCount());
//        System.out.println("Total Out Tokens used : "+ response.tokenUsage().outputTokenCount());
//        System.out.println("Finish Reason : " + response.metadata().finishReason());

//        System.out.println("------------------------------------------------------");
//
//        List<ChatMessage> conversation= new ArrayList<>();
//
//        conversation.add(SystemMessage.from("You are helpful assistant. Keep answer to one line "));
//
//        conversation.add(UserMessage.from("My name is Anuj and like to play Cricket."));
//
//        AiMessage aiMessage1 = chatModel.chat(conversation).aiMessage();
//
//        System.out.println("First : "+ aiMessage1.text());
//
//        conversation.add(aiMessage1);
//
//        conversation.add(UserMessage.from("What do i play ?"));
//
//        AiMessage aiMessage2 = chatModel.chat(conversation).aiMessage();
//
//        System.out.println("Second : " + aiMessage2.text());
//
//        conversation.add(aiMessage2);
//
//        conversation.add(UserMessage.from("I also play badminton and hockey too"));
//
//        AiMessage aiMessage3 = chatModel.chat(conversation).aiMessage();
//
//        System.out.println("Three : " + aiMessage3.text());
//
//        conversation.add(aiMessage3);
//
//        conversation.add(UserMessage.from("What do i play ?"));
//
//        AiMessage aiMessage4 = chatModel.chat(conversation).aiMessage();
//
//        System.out.println("Four : " + aiMessage4.text());

        System.out.println("------------------------------------------------------------");

        ChatRequest request = ChatRequest.builder()
                .messages(UserMessage.from("Give me the exact SQL to create a users table with id, email and created_at"))
                .modelName("gpt-4o")
                .temperature(0.0)
                .maxOutputTokens(150)
                .build();

        ChatResponse response = chatModel.chat(request);
        System.out.println(response.aiMessage().text());

        System.out.println(response.metadata().modelName());
    }
}
