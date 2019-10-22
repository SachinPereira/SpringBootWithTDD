    package com.example.task2.model;

import javax.persistence.*;

@Entity
@Table(name="task2")
public class StudentModel {
    @Id
    @GeneratedValue
    int id;
    @Column(name="name")
    String name;
    @Column(name="address")
    String address;
    @Column(name="phonenumber")
    String phonenumber;

    public StudentModel(String sachin, String jog, String s) {
        name=sachin;
        address=jog;
        phonenumber=s;
    }

    @Override
    public String toString() {
        return "studentModel{" +
                "idtask2=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number=" + phonenumber +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public StudentModel(int id, String name, String address, String phonenumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public StudentModel() {
        super();
    }
}
