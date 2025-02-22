package com.otpapp.otp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otpapp.otp.api.SmsSender;
import com.otpapp.otp.dto.AdminInfoDto;
import com.otpapp.otp.dto.EnquiryDto;
import com.otpapp.otp.dto.StudentInfoDto;
import com.otpapp.otp.model.AdminInfo;
import com.otpapp.otp.model.Enquiry;
import com.otpapp.otp.model.StudentInfo;
import com.otpapp.otp.service.AdminInfoRepo;
import com.otpapp.otp.service.EnquiryRepo;
import com.otpapp.otp.service.StudentInfoRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	EnquiryRepo erepo;

	@Autowired
	StudentInfoRepo srepo;
	
	@Autowired
	AdminInfoRepo adrepo;

	@GetMapping("/register")
	public String ShowRegister(Model model) {
		StudentInfoDto sd = new StudentInfoDto();
		model.addAttribute("sd", sd);
		return "register";
	}

	@PostMapping("/register")
	public String submitRegistration(@ModelAttribute StudentInfoDto studentInfoDto, 
			RedirectAttributes redirectAttributes) {
		try {
			StudentInfo si = new StudentInfo();
			si.setEnrollmentno(studentInfoDto.getEnrollmentno());
			si.setName(studentInfoDto.getName());
			si.setContactno(studentInfoDto.getContactno());
			si.setWhatsappno(studentInfoDto.getWhatsappno());
			si.setEmailaddress(studentInfoDto.getEmailaddress());
			si.setPassword(studentInfoDto.getPassword());
			si.setCollegename(studentInfoDto.getCollegename());
			si.setCourse(studentInfoDto.getCourse());
			si.setBranch(studentInfoDto.getBranch());
			si.setYear(studentInfoDto.getYear());
			si.setHighschool(studentInfoDto.getHighschool());
			si.setIntermediate(studentInfoDto.getIntermediate());
			si.setAggregatemarks(studentInfoDto.getAggregatemarks());
			si.setTrainingmode(studentInfoDto.getTrainingmode());
			si.setTraininglocation(studentInfoDto.getTraininglocation());
			si.setRegdate(new Date() + "");
			srepo.save(si);
			redirectAttributes.addFlashAttribute("message", "Registered Successfully");
			return "redirect:/register";

		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("message", "something went wrong " + ex.getMessage());
			return "redirect:/register";
		}

	}

	@GetMapping("/index")
	public String ShowIndex() {

		return "index";
	}

	@GetMapping("/aboutus")
	public String ShowAboutUS()

	{
		return "aboutus";
	}
	
	@GetMapping("/adminlogin")
	public String ShowAdminLogin(Model model)
	{
		AdminInfoDto dto = new AdminInfoDto();
		model.addAttribute("dto", dto);
		return "adminlogin";
	}
	
	
	@PostMapping("/adminlogin")
	public String AdminLogin(@ModelAttribute AdminInfoDto adminInfoDto,  HttpSession session, RedirectAttributes redirectAttributes)
	{
		try {
			
			AdminInfo adinfo = adrepo.getById(adminInfoDto.getUserid());
			if(adinfo.getPassword().equals(adminInfoDto.getPassword()))
			{
				//redirectAttributes.addFlashAttribute("msg", "Valid User");
				session.setAttribute("adminid", adminInfoDto.getUserid());
				return "redirect:/admin/adhome";
			}
			
			else
			{
				redirectAttributes.addFlashAttribute("msg", "Invalid User");
				return "redirect:/adminlogin";
			}
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "User does not Exist..!" +e.getMessage());
			return "redirect:/adminlogin";
		}
		
	
	}
	
	@GetMapping ("/student")
	public String ShowStudent(Model model)
	{
		StudentInfoDto dto =new StudentInfoDto();
		model.addAttribute("dto", dto);
		return "student";
	}
	
	
	@PostMapping("/student")
	public String ValidateStudent(@ModelAttribute StudentInfoDto dto, HttpSession session, RedirectAttributes attrib)
	{
	  try {
		  StudentInfo s=srepo.getById(dto.getEmailaddress());
		  if(s.getPassword().equals(dto.getPassword())) {
			  //attrib.addFlashAttribute("msg", "Valid User");//
			  session.setAttribute("studentid", s.getEmailaddress());
			  return "redirect:/student/stdhome"; 
		  }
		  else {
			  attrib.addFlashAttribute("msg", "Invalid User");
		  }
		  
		  return "redirect:/student";
	  }
	  
	  catch(EntityNotFoundException ex){
		  attrib.addFlashAttribute("msg", "Student doesn't exist");
		  return "redirect:/student";
		  
	  }
	
	
	
	}
	
	
	
	
	
	
	@GetMapping("/contactus")

	public String ShowContactUs(Model model) {
		EnquiryDto dto = new EnquiryDto();
		model.addAttribute("dto", dto);
		return "contactus";
	}
	
	
	
	
	

	@PostMapping("/contactus")
	public String SubmitEnquiry(@ModelAttribute EnquiryDto enquiryDto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {

			Enquiry eq = new Enquiry();
			eq.setName(enquiryDto.getName());
			eq.setGender(enquiryDto.getGender());
			eq.setContactno(enquiryDto.getContactno());
			eq.setEmailaddress(enquiryDto.getEmailaddress());
			eq.setEnquirytext(enquiryDto.getEnquirytext());
			eq.setPosteddate(new Date() + "");
			erepo.save(eq);
			SmsSender ss=new SmsSender();
			ss.sendSms(enquiryDto.getContactno()); 

			redirectAttributes.addFlashAttribute("message", "Form Submitted Successfully");

			return "redirect:/contactus";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Something Went Wrong");
			return "redirect:/contactus";
		}
	}

}
