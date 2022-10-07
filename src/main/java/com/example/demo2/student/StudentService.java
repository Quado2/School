package com.example.demo2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
     Optional<Student> existStudent =  studentRepository.findStudentByEmail(student.getEmail());
     if(existStudent.isPresent()){
         throw new IllegalStateException("Email already exist");
     } else {
         studentRepository.save(student);
     }
    }

    public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if(!exists){
        throw new IllegalStateException("Student with "+studentId + " does not exist");
    }

    studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id "+studentId+ " does not exist"));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if(optionalStudent.isPresent()){
                throw new IllegalStateException("Email is already taken");
            }
            student.setEmail(email);
        }
    }
}
