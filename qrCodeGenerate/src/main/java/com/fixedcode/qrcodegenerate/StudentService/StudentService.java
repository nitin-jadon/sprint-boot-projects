package com.fixedcode.qrcodegenerate.StudentService;

import com.fixedcode.qrcodegenerate.Student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }
    public Student addStudent(Student student)
    {
        Student save = studentRepository.save(student);
        return save;
    }
    public Student findById(Long id)
    {
        return studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found."));
    }



}
