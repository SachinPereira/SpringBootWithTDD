package com.example.task2.service;

import com.example.task2.model.StudentModel;

import java.util.List;

public interface StudentService {
    public StudentModel createNewRecord(StudentModel studentEntry);
    public List<StudentModel> getAllRecords();
    public StudentModel deleteRecord(int id);
    public StudentModel updateRecord(StudentModel student);
    public StudentModel findStudent(int id);

}
