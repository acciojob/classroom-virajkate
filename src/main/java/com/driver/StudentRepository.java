package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Component
@Repository
public class StudentRepository {
   private HashMap<String,Student> stdMap;
   private HashMap<String,Teacher> teachMap;
   private HashMap<String, List<String>> teacherstudentMapping;

    public StudentRepository() {
    this.stdMap=new HashMap<String,Student>();
    this.teachMap=new HashMap<String,Teacher>();
    this.teacherstudentMapping=new HashMap<String,List<String>>();
    }

    public void addStudent(Student student){
        stdMap.put(student.getName(),student);
    }
    // Adding Teacher Objects
    public void addTeacher(Teacher t) {
        teachMap.put(t.getName(),t);
    }

    // Pair an Existing Student and Teacher
    public void teacherStudentPair(String studentName, String teacherName) {
        if (stdMap.containsKey(studentName) && teachMap.containsKey(teacherName)) {

            List<String> listOfStudents = new ArrayList<>();
            if (teacherstudentMapping.containsKey(teacherName)) {
                listOfStudents = teacherstudentMapping.get(teacherName);
            }

            listOfStudents.add(studentName);

            teacherstudentMapping.put(teacherName,listOfStudents);
        }
    }

    // Find Student by Student Name
    public Student findStudent(String studentName) {
        return stdMap.get(studentName);
    }

    // Find Teacher by Teacher Name
    public Teacher findTeacher(String teacherName) {
        return teachMap.get(teacherName);
    }

    // Get List of Student by TeacherName
    public List<String> getStudentByTeacher(String teacherName) {
        List<String> listOfStudentNames = new ArrayList<>();
        if (teacherstudentMapping.containsKey(teacherName)) {
            listOfStudentNames = teacherstudentMapping.get(teacherName);
        }

        return listOfStudentNames;
    }

    // Get List of All Students added
    public List<String> findAllStudentNames() {
        List<String> allStudentNames = new ArrayList<>();

        for (String name : stdMap.keySet())
            allStudentNames.add(name);

        return allStudentNames;
    }

    // Delete a Teacher and Student Pair
    public void deletePair(String teacherName) {
        List<String> studentNames = new ArrayList<>();
        if (teacherstudentMapping.containsKey(teacherName)) {
            studentNames = teacherstudentMapping.get(teacherName);

            // Delete Students from Student Map
            for (String name : studentNames)
                if (stdMap.containsKey(name)) {
                    stdMap.remove(name);
                }

            // Remove Teacher and Student pair from TeacherStudentMap
            teacherstudentMapping.remove(teacherName);
        }

        // Now Delete Teacher from teacherMap also
        if (teachMap.containsKey(teacherName)) {
            teachMap.remove(teacherName);
        }
    }

    // Delete all Teachers and Students Records
    public void deleteAllRecords() {

        // Making a HashSet to Delete all records Because Deleting in HashMap Directly can occur error
        HashSet<String> studentSet = new HashSet<>();

        // add all Students names in Set to delete them
        for (String teacherName : teacherstudentMapping.keySet()) {
            for (String studentName : teacherstudentMapping.get(teacherName)) {
                studentSet.add(studentName);
            }
        }

        // Now Delete all records
        for (String student : studentSet) {
            if (stdMap.containsKey(student)) {
                stdMap.remove(student);
            }
        }
    }
}
