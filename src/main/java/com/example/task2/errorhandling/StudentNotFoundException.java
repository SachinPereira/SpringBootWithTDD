package com.example.task2.errorhandling;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String phonenumber){
        super("Phone number invalid "+phonenumber);
    }
    public StudentNotFoundException(int id){
        super("Student Doesnot Exists"+id);
    }


}
