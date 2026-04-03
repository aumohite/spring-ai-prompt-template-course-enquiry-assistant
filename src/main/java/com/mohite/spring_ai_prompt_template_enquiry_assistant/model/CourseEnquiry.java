package com.mohite.spring_ai_prompt_template_enquiry_assistant.model;

/**
 * Object to hold the enquiry data from client call.
 */
public record CourseEnquiry(String name, String email, String question) {

}
