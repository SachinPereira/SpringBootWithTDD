package com.example.task2.UnitTesting;
import com.example.task2.controller.ControllerCrud;
import com.example.task2.model.StudentModel;
import com.example.task2.service.StudentServiceImpli;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class unitTestService {
    @InjectMocks
    private ControllerCrud controller;
    @Mock
    private StudentServiceImpli service;

    @Test
    public void createStudent(){
        StudentModel student=new StudentModel();
        when(service.createNewRecord(student)).thenReturn(student);
        assertEquals(student,controller.createRecord(student));
    }
    @Test
    public void displayStudent(){
        List<StudentModel> response=new ArrayList<StudentModel>();
        when(service.getAllRecords()).thenReturn(response);
        assertEquals(response,controller.getAllStudent());
    }
    @Test
    public void findStudent(){
        int id=1;
        when(service.findStudent(id)).thenReturn(new StudentModel(1,"sachin","Jog","788"));
        assertThat(controller.findStudent(1).getName()).isEqualTo("sachin");
        assertThat(controller.findStudent(1).getId()).isEqualTo(1);
        assertThat(controller.findStudent(1).getPhonenumber()).isEqualTo("788");
        assertThat(controller.findStudent(1).getAddress()).isEqualTo("Jog");

    }
    @Test
    public void delete(){
        int id=1;
        when(service.deleteRecord(1)).thenReturn(new StudentModel(1,"sachin","Jog","777"));
        assertThat(controller.deleteStudent(1).getBody().getId()).isEqualTo(1);
        assertThat(controller.deleteStudent(1).getBody().getName()).isEqualTo("sachin");
        assertThat(controller.deleteStudent(1).getBody().getAddress()).isEqualTo("Jog");
        assertThat(controller.deleteStudent(1).getBody().getPhonenumber()).isEqualTo("777");
    }
    @Test
    public void update(){
        StudentModel st=new StudentModel(1,"sachin","Jog","777");
        when(service.updateRecord(st)).thenReturn(new StudentModel(1,"sachin","Jog","777"));
        assertThat(controller.updateInfo(st).getName()).isEqualTo("sachin");
        assertThat(controller.updateInfo(st).getPhonenumber()).isEqualTo("777");
        assertThat(controller.updateInfo(st).getAddress()).isEqualTo("Jog");
        assertThat(controller.updateInfo(st).getId()).isEqualTo(1);
    }
}
