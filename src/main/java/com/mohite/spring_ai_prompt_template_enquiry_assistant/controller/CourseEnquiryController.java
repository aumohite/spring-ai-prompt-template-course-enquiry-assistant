package com.mohite.spring_ai_prompt_template_enquiry_assistant.controller;

import org.springframework.web.bind.annotation.*;

import com.mohite.spring_ai_prompt_template_enquiry_assistant.model.CourseEnquiry;
import com.mohite.spring_ai_prompt_template_enquiry_assistant.service.CourseEnquiryService;
import com.mohite.spring_ai_prompt_template_enquiry_assistant.service.CourseEnquiryServiceusingPromptTmpl;
import com.mohite.spring_ai_prompt_template_enquiry_assistant.service.Enums.ProcessingMode;

@RestController
@RequestMapping("/course-enquiries")
public class CourseEnquiryController {

	private final CourseEnquiryServiceusingPromptTmpl courseEnquiryServiceusingPromptTmpl;

	private final CourseEnquiryService courseEnquiryService;

	public CourseEnquiryController(CourseEnquiryService courseEnquiryService,
			CourseEnquiryServiceusingPromptTmpl courseEnquiryServiceusingPromptTmpl) {
		super();
		this.courseEnquiryService = courseEnquiryService;
		this.courseEnquiryServiceusingPromptTmpl = courseEnquiryServiceusingPromptTmpl;
	}

//	@GetMapping("/enquire")
//	public String enquire(@RequestBody CourseEnquiry courseEnquiry) {
//		
//		String name = courseEnquiry.email();
//		//return courseEnquiryService.processEnquiry(courseEnquiry);
//		return courseEnquiryServiceusingPromptTmpl.processEnquiry(courseEnquiry);
//	}

	//usage:
	//http://localhost:8088/course-enquiries/DIRECT
	//http://localhost:8088/course-enquiries/DIRECT
	@PostMapping("/{mode}")
	public String generateResponse(@PathVariable ProcessingMode mode, 
			@RequestBody CourseEnquiry courseEnquiry) {

		System.out.println("mode:"+mode);
		switch (mode) {
		case TEMPLATE:
			return courseEnquiryServiceusingPromptTmpl.generateCourseEnquiryResponseWithTemplate(courseEnquiry);
		case DIRECT:
			return courseEnquiryService.generateCourseEnquiryResponseDirectly(courseEnquiry);
		default:
			throw new IllegalArgumentException("Invalid mode");
		}
	}

}

//GET /course-enquiries?question=Java&mode=template
//GET /course-enquiries?question=Java&mode=direct


