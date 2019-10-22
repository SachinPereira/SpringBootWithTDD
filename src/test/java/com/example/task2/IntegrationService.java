package com.example.task2;

import com.example.task2.controller.ControllerCrud;
import com.example.task2.model.StudentModel;
import com.example.task2.repository.StudentRepository;
import com.example.task2.service.StudentServiceImpli;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationService {
    @Mock
    private StudentRepository repo;
    private StudentServiceImpli service;
    @Before
    public void setUp(){
        service = new StudentServiceImpli(repo);
    }
    @Test
    public void getStudentById(){
        given(repo.findById(1)).willReturn(java.util.Optional.of(new StudentModel(1, "sachin", "Jog", "99399")));
        StudentModel stModel=service.findStudent(1);
        assertThat(stModel.getId()).isEqualTo(1);
        assertThat(stModel.getName()).isEqualTo("sachin");
    }
    @Test
    public void createStudent(){
        StudentModel entity= new StudentModel(1,"sachin","Jog","6789");
        given(repo.save(entity)).willReturn(new StudentModel(1,"sachin","Jog","6789"));
        assertThat(entity.getName()).isEqualTo("sachin");
        assertThat(entity.getAddress()).isEqualTo("Jog");
    }

    @RunWith(SpringRunner.class)
    @WebMvcTest(ControllerCrud.class)
    public static class unitTestController {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private StudentRepository repo;
        private StudentServiceImpli service;
        @Before
        public void setUp()throws Exception{
            service=new StudentServiceImpli(repo);
        }
        @Test
        public void getStudents()throws Exception{
          //  given(service.getAllRecords()).willReturn((List<studentModel>) new studentModel(1,"sachin","Jog","5678"));
            mockMvc.perform(MockMvcRequestBuilders.get("/createStudent"));

    //                .andExpect(jsonPath("address").value("Jog"))
    //                .andExpect(jsonPath("idtask2").value(1));
        }
        @Test
        public void findStudentTest() throws Exception{
           given(service.findStudent(1)).willReturn(new StudentModel(1,"sachin","Jog","6789"));

            System.out.println(service.findStudent(1).getName());
            mockMvc.perform(MockMvcRequestBuilders.get("/findStudent/1"));
                   // .andExpect(jsonPath("name").value("sachin"));
        }



    }
}
