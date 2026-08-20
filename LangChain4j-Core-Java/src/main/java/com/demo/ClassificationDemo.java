package com.demo;

import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import jdk.jfr.Description;

public class ClassificationDemo {

    enum Module{
        @Description("Questions about Java, OPPS, Collections, JVM")
        JAVA_BASICS,
        @Description("Questions about Spring, Spring Boot, Rest APIs, JPA")
        SPRING_BOOT,
        @Description("Questions about AI and LangChain4j")
        LANGCHAIN4J,
        @Description("Payment , Login , certification and other support related issues.")
        SUPPORT
    }


    interface DoubtRouter{

        @UserMessage("Which part of the course does this student doubt belong to?\n\n : {{doubt}}")
        Module route(@V("doubt") String doubt);

        @UserMessage("Does this needs a human mentor rather than a automated answer?\n\n : {{doubt}}")
        boolean needsMentor(@V("doubt") String doubt);
    }

    public static void main(String[] args) {

        DoubtRouter assistant = AiServices.create(DoubtRouter.class, Models.chat());

        String [] doubts =
                {
                        "I paid yesterday but course is not showing in My courses.",
                        "What is the difference between abstract class and an interface.",
                        "My chatMemory is not remembering data anything between two calls in my ai application."
                };

        for(String doubt : doubts){
            Module module = assistant.route(doubt);

            String queue = switch (module)
            {
                case SUPPORT -> "Support Team";
                case JAVA_BASICS, SPRING_BOOT -> "Mentor Team";
                case LANGCHAIN4J -> "AI Team";
            };

            System.out.println("Question : " + doubt);

            System.out.println("Routed to : " + module + " Queue : " + queue);

        }

        System.out.println(assistant.needsMentor("I have been stuck for three days and nothing is helping me."));
    }

}
