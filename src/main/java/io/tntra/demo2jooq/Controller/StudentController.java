package io.tntra.demo2jooq.Controller;


import io.tntra.demo2jooq.Entity.Student;
import io.tntra.demo2jooq.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {

    public StudentRepo studentRepo;

    @Autowired
    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public Student findStudentById(@PathVariable("studentId") long id, Student student){
        List<Student> StudentList=studentRepo.findStudentById(id);
        return student;
    }


}
