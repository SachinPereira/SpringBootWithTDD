package com.example.task2.IntegrationTest;

import com.example.task2.Task2Application;
import com.example.task2.model.StudentModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Task2Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerMockmvcTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private String getRootUrl() {
        return "http://localhost:" + port + "/v1";
    }

    @Test
    public void testCreateStudent() {
        StudentModel studentModel = new StudentModel();
        studentModel.setName("admin@gmail.com");
        studentModel.setAddress("admin");
        studentModel.setPhonenumber("9876543210");
        System.out.println(studentModel);
        ResponseEntity<StudentModel> postResponse = restTemplate.postForEntity(getRootUrl() + "/student", studentModel, StudentModel.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode(), "Test case create Sucesss");
        System.out.println("Response" + postResponse);
    }
    @Test
    public void studentFailTestCase() {
        StudentModel studentModel = new StudentModel();
        studentModel.setName("admin@gmail.com");
        studentModel.setAddress("admin");
        studentModel.setPhonenumber("987654321");
        System.out.println(studentModel);
        ResponseEntity<StudentModel> postResponse = restTemplate.postForEntity(getRootUrl() + "/student", studentModel, StudentModel.class);
        assertEquals(HttpStatus.BAD_REQUEST, postResponse.getStatusCode(), "Test case create Sucesss");
    }
    @Test
    public void testGetAllStudents() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students",
                HttpMethod.GET, entity, String.class);
        System.out.println(response.getStatusCode());
        assertEquals(response.getStatusCode(),HttpStatus.OK,"RetriveData TestCase Sucess");
    }

    @Test
    public void testUpdateStudent() {
        StudentModel studentModel=new StudentModel(1,"sachin","Jog","9876543210");
        studentModel.setId(1);
        studentModel.setAddress("admin");
        ResponseEntity<StudentModel> respone = restTemplate.postForEntity(getRootUrl() + "/updateStudent/", studentModel,StudentModel.class);
        System.out.println(respone.getStatusCode());
        assertEquals(respone.getStatusCode(),HttpStatus.OK," Update TestCase Sucess");
    }


}
