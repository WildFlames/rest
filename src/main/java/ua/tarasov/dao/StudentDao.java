package ua.tarasov.dao;

import java.util.List;

import ua.tarasov.model.Student;

public interface StudentDao {

	List<Student> allStudents(int page);

	List<Student> getAllStudents();
	
	Student getById(Long id);
	
	void add(Student student);

	void update(Long id, Student student);

	void delete(Student student);
}
