package com.example.task2;

import com.example.task2.model.StudentModel;
import com.example.task2.repository.StudentRepository;
import com.example.task2.service.StudentServiceImpli;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Task2TestController {
   @Autowired
   StudentServiceImpli service;
   @MockBean
   StudentRepository repo;
    @Test
    public void testRetrieveStudentWithMockRepository() throws Exception {
        Optional<StudentModel> optStudent = Optional.of( new StudentModel(1,"sachin","Jog","98765"));
        when(repo.findById(1)).thenReturn(optStudent);
        assertTrue(service.findStudent(1).getName().contains("sachin"));
    }

}
