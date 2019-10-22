package com.example.task2.controller;

import com.example.task2.errorhandling.StudentNotFoundException;
import com.example.task2.model.StudentModel;
import com.example.task2.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.task2.errorhandling.ErrorHandling;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@RestController
@RequestMapping(value = "v1")
public class ControllerCrud {
    ErrorHandling errorDisplay;
    private static final Logger LOG = LogManager.getLogger(ControllerCrud.class);
    @Autowired
    private StudentService studentservice;

    @PostMapping(value = "/student", produces = "application/json")
    public StudentModel createRecord(@RequestBody StudentModel model) {

        return studentservice.createNewRecord(model);

    }

    @GetMapping(value = "/students", produces = "application/json")
    public List<StudentModel> getAllStudent() {
        List<StudentModel> st;
        st = studentservice.getAllRecords();
        if (st.size() > 0) {
            return st;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, " Students not found");
        }
    }

    @PostMapping(value = "/updateStudent", produces = "application/json")
    public StudentModel updateInfo(@RequestBody StudentModel studentModel) {
        return studentservice.updateRecord(studentModel);
    }

    @DeleteMapping(value = "/deleteStudent/{id}", produces = "application/json")
    public ResponseEntity<StudentModel> deleteStudent(@PathVariable int id) {
        StudentModel std = null;
        try {
            std = studentservice.deleteRecord(id);
        } catch (StudentNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, " StudentNot Found");
        }
        return new ResponseEntity<StudentModel>(std, HttpStatus.OK);
    }

    @GetMapping(value = "/student/{id}", produces = "application/json")
    public StudentModel findStudent(@PathVariable int id) {
        try {
            return studentservice.findStudent(id);
        } catch (StudentNotFoundException e) {
            LOG.error("Student not found" + e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, " StudentNot " + id + " Found");
        }
    }
}
