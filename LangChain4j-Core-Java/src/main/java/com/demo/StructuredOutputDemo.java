package com.demo;

import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import jdk.jfr.Description;

import java.util.List;

public class StructuredOutputDemo {

        record CourseEnquiry(
                @Description("Full name of the person")
                String name,

                @Description("Course they want to learn")
                String topic,

                @Description("Year of experience")
                int experienceYears,

                @Description("Weather they are ready to enroll")
                boolean readyToEnrol
        ){ }


        interface EnquiryReder
        {
            @UserMessage("Extract the enquiry details from this message: \n\n{{message}}")
            CourseEnquiry read(@V("message") String message);


            @UserMessage("List the programming topics mentioned in this text: {{text}}")
            List<String> topicsIn(@V("text") String text);

            @Description("Extract the enquiry details from this message : \n\n{{message}}")
            Result<CourseEnquiry> readWithMetaData(@V("message") String message);
        }

    public static void main(String[] args) {

        EnquiryReder assistant = AiServices.create(EnquiryReder.class, Models.chat());

        String message =
                """
                    Hi, I am Anuj Vaidya. I want to learn Spring Boot and Microservices.
                    I have 3 years of experience in java development.
                    I am ready to enroll in course  .
                """;

        CourseEnquiry enquiry = assistant.read(message);

        System.out.println(enquiry);

        String text = "I know SpringBoot and Java, However I don't know Redis and Kafka well.";

        System.out.println(assistant.topicsIn(text));

        Result<CourseEnquiry> result = assistant.readWithMetaData(message);

        System.out.println(result.content());

        System.out.println("--------------------------");

        System.out.println("Input Tokens : " +result.tokenUsage().inputTokenCount());

        System.out.println("--------------------------");

        System.out.println("Output Token : " +result.tokenUsage().outputTokenCount());
    }
}
