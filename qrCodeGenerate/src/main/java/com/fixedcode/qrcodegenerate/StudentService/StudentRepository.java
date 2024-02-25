package com.fixedcode.qrcodegenerate.StudentService;

import com.fixedcode.qrcodegenerate.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
