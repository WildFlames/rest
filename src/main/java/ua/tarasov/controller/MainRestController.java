package ua.tarasov.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.tarasov.model.ExcelWriter;
import ua.tarasov.model.Student;
import ua.tarasov.service.StudentService;

@RestController
@RequestMapping("list")
public class MainRestController {

	@Autowired
	private StudentService studendService;
	
	@GetMapping()
	public List<Student> list(@RequestParam(defaultValue = "1") int page){
		return studendService.allStudents(page);
	}
	
	@GetMapping("{id}")
	public Student getOne(@PathVariable("id") Long id) {
		return studendService.getById(id);
	}
	
	@PostMapping("export")
	public void export() throws InvalidFormatException, IOException {
		ExcelWriter.main(studendService.getAllStudents());
	}
	
	@PostMapping()
	public void post(@RequestBody Student student) {
		studendService.add(student);
	}
	
	@PutMapping("{id}")
	public void put(@PathVariable ("id") Long id, @RequestBody Student student) {
		studendService.update(id, student);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable ("id") Long id) {
		studendService.delete(studendService.getById(id));
	}
}