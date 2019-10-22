package com.example.task2.UnitTesting;
import com.example.task2.model.StudentModel;
import com.example.task2.repository.StudentRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.example.task2.service.StudentServiceImpli;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class unitTestRepository {
	@InjectMocks
	private StudentServiceImpli service;
	@Mock
	private StudentRepository repo;
	@Test
	public void contextLoads() {
	}
	@Test
	public void getAllRecords(){
		when(repo.findAll()).thenReturn((Iterable<StudentModel>) Stream.of(new StudentModel(1,"ff","dsd","dskkd")).collect(Collectors.toList()));
		assertEquals(1,service.getAllRecords().size());
	}
	@Test
	public void CreateNewRecord(){
		StudentModel newstudent=new StudentModel();
		when(repo.save(newstudent)).thenReturn(newstudent);
		assertEquals(newstudent,service.createNewRecord(newstudent));
	}
	@Test
	public void updateRecord(){
		StudentModel newstudent=new StudentModel();
		when(repo.save(newstudent)).thenReturn(newstudent);
		assertEquals(newstudent,service.updateRecord(newstudent));
	}
	@Test
	public void findById(){
		Optional<StudentModel> searchStudent=Optional.of(new StudentModel(1,"sachin","Jog","64333"));
		when(repo.findById(1)).thenReturn(searchStudent);
		assertTrue(service.findStudent(1).getName().equals("sachin"));
	}

}
