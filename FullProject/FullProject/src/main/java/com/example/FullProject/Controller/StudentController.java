package com.example.FullProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.FullProject.Entity.Student;
import com.example.FullProject.Entity.Validation;
import com.example.FullProject.Service.Studentservice;

@Controller
public class StudentController {
	private Studentservice studentServ;
	public StudentController (Studentservice studentServ) {// constructor
		this.studentServ=studentServ;
	}
	@GetMapping("/addNewStudent")
	public String createStudent(Model model) {
		Student stdcreateObj= new Student();
		model.addAttribute("stdObj", stdcreateObj);
		return "createStudent";
	}
	@GetMapping("/seeallstudents")// to see all the students
	public String getAllStudentsFromDb(Model model){
		model.addAttribute("studentsList", studentServ.readAllStudents());
		return "studentFrontEnd";

	}
	@PostMapping("/insertStudents")
	public String insertToDb(@ModelAttribute("stdObj")  Student std) {
		studentServ.saveStudent(std);  //obj is insert into db by using save 
		return "redirect:/seeallstudents";
	}
	@GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable int id, Student fromdb,Model model) {

        model.addAttribute("update" ,studentServ.updatestudent(id,fromdb));
        return "updateStudent";
    }
	@PostMapping("/updateAndSaveStudent/{id}")
	public String updateandSave(@PathVariable int id, @ModelAttribute("update")Student newfromdb ,Student fromdb) {
		Student existingdb=studentServ.updatestudent(id,fromdb);
		existingdb.setFirstname(newfromdb.getFirstname());
		existingdb.setLastname(newfromdb.getLastname());
		existingdb.setFavSub(newfromdb.getFavSub());
		return "redirect:/seeallstudents";
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteFromDb(@PathVariable int id) {
		
		studentServ.deleteStudent(id);
		return "redirect:/seeallstudents";
		
	}
	@GetMapping("/HomePage")
	public String getAlldataFromHome(Model model){
		model.addAttribute("studentsList", studentServ.readAllStudents());
		return "HOME";

}
	
	@GetMapping("/CONTACT")
	public String insertToDb2(@ModelAttribute("stdObj")  Student std) {
		studentServ.saveStudent(std);  //obj is insert into db by using save 
		return "contact";
}
	@GetMapping("/login")
	public String loginPage(Model model) {
		Validation admin=new Validation();
		model.addAttribute("adminObj",admin);
		return "login";
	}
	@GetMapping("/logout")
	public String logoutPage(Model model) {
		return "logout";
	}
	@GetMapping("/validatingData")
	public String validateLoginCredentials(@ModelAttribute("adminObj")Validation adminDetails) {
		if(adminDetails.getUserName().equals("Adminlogin")&& adminDetails.getPassword().equals("1234abcd")) {
			return "redirect:HomePage";
		}
		else {
			return "login";
		}
		
	}
	@GetMapping("/photogallery")
	public String galler(Model model) {
		return "photogallery";
}
}


