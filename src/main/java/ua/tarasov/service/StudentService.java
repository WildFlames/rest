package ua.tarasov.service;

import java.util.List;

import ua.tarasov.model.Student;

public interface StudentService {

	List<Student> allStudents(int page);

	Student getById(Long id);

	void add(Student student);

	void update(Long id, Student student);

	void delete(Student student);

	List<Student> getAllStudents();

}
