package com.example.task2.UnitTesting;

import com.example.task2.controller.ControllerCrud;
import com.example.task2.model.StudentModel;
import com.example.task2.service.StudentServiceImpli;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class unitTestController {

    @InjectMocks
    private ControllerCrud controller;
    @Mock
    private StudentServiceImpli service;

    @Test
    public void createStudent() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(service.createNewRecord(any(StudentModel.class))).thenReturn(new StudentModel(1, "Lokesh", "Gupta", "9876543211"));
        StudentModel employee = new StudentModel(1, "Lokesh", "Gupta", "9876543211");
        StudentModel response = controller.createRecord(employee);
        assertThat(response.getName()).isEqualTo("Lokesh");

    }

    @Test
    public void deleteStudent() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(service.deleteRecord(1)).thenReturn(new StudentModel(1, "Lokesh", "Gupta", "9876543211"));
        StudentModel employee = new StudentModel(1, "Lokesh", "Gupta", "9876543211");
        ResponseEntity<StudentModel> response = controller.deleteStudent(1);
        assertThat(response.getBody().getName()).isEqualTo("Lokesh");

    }

    @Test
    public void findStudent() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(service.findStudent(1)).thenReturn(new StudentModel(1, "Lokesh", "Gupta", "9876543211"));
        StudentModel employee = new StudentModel(1, "Lokesh", "Gupta", "9876543211");
        StudentModel response = controller.findStudent(1);
        assertThat(response.getName()).isEqualTo("Lokesh");
    }

    @Test
    public void updateStudent() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(service.updateRecord(any(StudentModel.class))).thenReturn(new StudentModel(1, "Lokesh", "Gupta", "9876543211"));
        StudentModel employee = new StudentModel(1, "Lokesh", "Gupta", "9876543211");
        StudentModel response = controller.updateInfo(employee);
        assertThat(response.getName()).isEqualTo("Lokesh");
    }
}
