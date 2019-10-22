package com.example.task2.service;

import com.example.task2.errorhandling.StudentNotFoundException;
import com.example.task2.model.StudentModel;
import com.example.task2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpli implements StudentService {
    @Autowired
    StudentRepository repository;
    StudentNotFoundException exception;

    public StudentServiceImpli(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentModel createNewRecord(StudentModel studentEntry) {
            return repository.save(studentEntry);
    }

    @Override
    public List<StudentModel> getAllRecords() {
        return (List<StudentModel>) repository.findAll();
    }

    @Override
    public StudentModel deleteRecord(int id) {
        StudentModel result;
        Optional<StudentModel> student = repository.findById(id);
        student.orElseThrow(() -> new StudentNotFoundException(id));
        repository.deleteById(id);
        return student.get();

    }

    @Override
    public StudentModel updateRecord(StudentModel student) {
        return repository.save(student);
    }

    @Override
    public StudentModel findStudent(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

    }


}
