package com.mohite.spring_ai_prompt_template_enquiry_assistant.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.mohite.spring_ai_prompt_template_enquiry_assistant.model.CourseEnquiry;

/**
 * Generates the Response for the course queries Using PromptTemplate.
 */
@Service
public class CourseEnquiryService {

	private ChatClient chatclient;
	
	private String enquiryTemplate = """
			You are an assistant who helps to handle enquiries for THE ACADEMY SCHOOL.THE ACADEMY SCHOOL is an online platform for training on various courses.
			This academy is founded and run by Ashish Mohite.
			
			Student details:
			- Name:{studentName}
			- email:{studentEmail}
			
			 Enquiry message: 
			 \\"\\"\\
			 {enquiryMessage}
			 \\"\\"\\
			 
			 THE ACADEMY SCHOOL currently offers courses on AI impemnation using Spring-AI, SpringBoot.It also offers courses on Angular, React and Microservices.
			 Typical Course fee ranges form INR 10000 to INR 30000. Payment options supported are Cash, Card, UPI,PayPal.
			 
			 Your tasks:
			 1.Briefly uderstand what the student is asking for. 
			 2.Draft a polite, clear and a friendly message in simple English.
			 	- Assume the reply is sent from "THE ACADEMY SCHOOL".
			 	- Encourage the studenmt to reply back if they have more questions.
			 3.Classify the enquiry in one of the following:
			 	-"Course Enquiry"
			 	-"technical_support"
			 	-"payment_or_refund"
			 	-"other"
			 	
			 Return the result in following JSON Format ONLY:
			 \\{
			 	"summary":"one or two line summary of what they asked",
			 	"reply":"the full repy that we can send to the student",
			 	"intent":"one of the four categories above"
			 \\}
			""";
	PromptTemplate promtTemplate = new PromptTemplate(enquiryTemplate);
	
	public CourseEnquiryService(ChatClient.Builder chatclient) {
		this.chatclient = chatclient.build();
	}

	public String generateCourseEnquiryResponseDirectly(CourseEnquiry courseEnquiry) {
		
		Prompt prompt = promtTemplate.create(Map.of("studentName", courseEnquiry.name(), "studentEmail",
				courseEnquiry.email(), "enquiryMessage", courseEnquiry.question()));
		String result = chatclient
				.prompt(prompt)
				.call()
				.content();
		return result;
	}
	
	public String generateCourseEnquiryResponseDirectly1(CourseEnquiry courseEnquiry) {
		
		Message userMsg = new UserMessage("handle the enquiry for the student with the details : " + "name:"
				+ courseEnquiry.name() + "email:" + courseEnquiry.email() + "question:" + courseEnquiry.question());
		Message systemMsg = new SystemMessage("You are a customer exeutive from the Academy School who writes replies"
				+ "in polite and friendly manner");
		Prompt prompt = new Prompt(List.of(userMsg, systemMsg));
		String result = chatclient
				// .prompt()
				// .user(courseEnquiry.question())
				.prompt(prompt)
				.call()
				.content();
		return result;
	}

}
