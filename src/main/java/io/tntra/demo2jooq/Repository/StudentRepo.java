package io.tntra.demo2jooq.Repository;

import io.tntra.demo2jooq.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findStudentById(Long id);
}
