package com.example.FullProject.Service;

import java.util.List;

import com.example.FullProject.Entity.Student;

public interface Studentservice {
	Student saveStudent(Student stdobj);
	List<Student>readAllStudents();
	Student fetchById(int studentId) throws Exception ;
	Student updatestudent(int id, Student value);
	void deleteStudent(int id);
	Student getStdntById(int id);
	List<Student> viewStudentsFromDB();


}
