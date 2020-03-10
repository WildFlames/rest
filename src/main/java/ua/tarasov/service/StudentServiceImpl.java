package ua.tarasov.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.tarasov.dao.StudentDao;
import ua.tarasov.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	@Transactional
	public List<Student> allStudents(int page) {
		return studentDao.allStudents(page);
	}

	@Override
	@Transactional
	public Student getById(Long id) {
		return studentDao.getById(id);
	}

	@Override
	@Transactional
	public void add(Student student) {
		studentDao.add(student);
	}
	
	@Override
	@Transactional
	public void update(Long id, Student student) {
		studentDao.update(id, student);
	}

	@Override
	@Transactional
	public void delete(Student student) {
		studentDao.delete(student);
	}

	@Override
	@Transactional
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}
}
