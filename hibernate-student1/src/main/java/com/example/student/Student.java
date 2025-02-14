package com.example.student;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNumber;

    @Column(name = "name", nullable = false)
    private String name;

    public Student() {}

    public Student(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
