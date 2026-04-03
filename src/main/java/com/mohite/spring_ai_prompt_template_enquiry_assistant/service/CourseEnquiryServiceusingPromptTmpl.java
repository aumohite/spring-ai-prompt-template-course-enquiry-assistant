package com.mohite.spring_ai_prompt_template_enquiry_assistant.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.mohite.spring_ai_prompt_template_enquiry_assistant.model.CourseEnquiry;

import jakarta.annotation.PostConstruct;

/**
 * Generates the Response for the course queries Using PromptTemplate.
 */
@Service
public class CourseEnquiryServiceusingPromptTmpl {

	private ChatClient chatclient;
	
	PromptTemplate promtTemplate = null;
	
	@Value("classpath:templates/prompts/courseenquirytemplate.st")
	private Resource enquiryTemplate;
	
	public CourseEnquiryServiceusingPromptTmpl(ChatClient.Builder chatclient) {
		this.chatclient = chatclient.build();
	}

	@PostConstruct
	public void init() {
		promtTemplate = new PromptTemplate(enquiryTemplate); 
	}
	
	public String generateCourseEnquiryResponseWithTemplate(CourseEnquiry courseEnquiry) {
		
		Prompt prompt = promtTemplate.create(Map.of("studentName", courseEnquiry.name(), "studentEmail",
				courseEnquiry.email(), "enquiryMessage", courseEnquiry.question()));
		String result = chatclient
				.prompt(prompt)
				.call()
				.content();
		
//		String result = chatclient
//				.prompt()
//				.user(PromptUserSpec promptUserSpec ->
//							promptUserSpec.text(enquiryTemplate)
//							.param("studentName", courseEnquiry.name())
//							.param("studentEmail",courseEnquiry.email())
//							.param("enquiryMessage", courseEnquiry.question()))
//				.call()
//				.content();
		return result;
	}
}
