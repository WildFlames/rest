package ua.tarasov.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.tarasov.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Student> allStudents(int page) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Student").setFirstResult(9 * (page - 1)).setMaxResults(10).list();
	}

	@Override
	public Student getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Student) session.get(Student.class, id);
	}

	@Override
	public void add(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student);
	}

	@Override
	public void update(Long id, Student student) {
		Session session = sessionFactory.getCurrentSession();
		Student studetnFromDB = session.byId(Student.class).load(id);
	      studetnFromDB.setFirstName(student.getFirstName());
	      studetnFromDB.setLastName(student.getLastName());
	      studetnFromDB.setAge(student.getAge());
	      studetnFromDB.setBirthdate(student.getBirthdate());
	      studetnFromDB.setFaculty(student.getFaculty());
	      session.flush();
	}

	@Override
	public void delete(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(student);
	}

	@Override
	public List<Student> getAllStudents() {
		Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Student> cq = cb.createQuery(Student.class);
	      Root<Student> root = cq.from(Student.class);
	      cq.select(root);
	      Query<Student> query = session.createQuery(cq);
	      return query.getResultList();
	}
}
