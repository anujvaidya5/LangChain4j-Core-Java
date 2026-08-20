package com.demo;

import dev.langchain4j.service.Result;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import jdk.jfr.Description;

import java.util.List;

public class StructuredOutputDemo {

    public static void main(String[] args) {

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
    }
}
