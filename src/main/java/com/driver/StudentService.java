package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
  public void addStudent(Student student){
      studentRepository.addStudent(student);
  }
    // to add Teacher
    public void addTeacher(Teacher t) {
        studentRepository.addTeacher(t);
    }

    // to add Teacher Student pair
    public void addTeacherStudentPair(String s, String t) {
        studentRepository.teacherStudentPair(s,t);
    }

    // Find Student by Student Name
    public Student findStudent(String s) {
        return studentRepository.findStudent(s);
    }

    // Find Teacher by Teacher name
    public Teacher findTeacher(String t) {
        return studentRepository.findTeacher(t);
    }

    // Find Students by Teacher Name
    public List<String> findStudentByTeacher(String t) {
        return studentRepository.getStudentByTeacher(t);
    }

    // find all Students Names
    public List<String> findAllStudents() {
        return studentRepository.findAllStudentNames();
    }

    // Delete a Student and Teacher pair
    public void deletePair(String teacherName) {
        studentRepository.deletePair(teacherName);
    }

    // Delete All Records
    public void deleteAllTeachers() {
        studentRepository.deleteAllRecords();
    }
}
