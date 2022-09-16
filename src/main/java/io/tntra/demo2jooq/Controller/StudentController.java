package io.tntra.demo2jooq.Controller;


import io.tntra.demo2Jooq.tables.daos.StudentDao;
import io.tntra.demo2Jooq.tables.pojos.Student;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.tntra.demo2Jooq.tables.Student.STUDENT;

@RestController
public class StudentController {

    private  final StudentDao studentDao;

    private  final DSLContext dsl;

    private final TransactionTemplate transactionTemplate;

    public StudentController(DSLContext dsl, Configuration jooqConfiguration,TransactionTemplate transactionTemplate){
        this.dsl=dsl;
        this.studentDao=new StudentDao(jooqConfiguration);
        this.transactionTemplate=transactionTemplate;

    }


    @GetMapping("/listStudent")
    public List<Student> Student(){
        return  this.studentDao.findAll();

    }
    @PostMapping("/deleteStudent")
    public void  delete(@RequestParam("id") int id){
        this.studentDao.deleteById(id);
    }

    @PostMapping("/newStudent")
    public Student newStudent(@RequestBody Student newStudent){
        this.studentDao.insert(newStudent);
        System.out.println(newStudent.getId());
        return  newStudent;
    }

    @PostMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student newStudent){
        this.studentDao.update(newStudent);
        return  newStudent;

    }

//    @GetMapping("/findStudents/{name}")
//    public  Integer[] findStudent(@PathVariable("name") String name){
//        return this.dsl
//                .select(STUDENT.ID)
//                .from(STUDENT)
//                .where(STUDENT.FIRST_NAME.contains(name).or(STUDENT.FIRST_NAME.contains(name)))
//                .fetchArray(STUDENT.ID);
//    }




    @GetMapping("/insertMultiple1")
    @Transactional
    public void insertMultiple1() {
        Student s1 = new Student();
        s1.setId(1);
        s1.setFirstName("1");
        s1.setLastName("1");
        s1.setAge(1);
        s1.setEmail("1");
        s1.getCollageName("1");

        this.studentDao.insert(s1);

        if (true) {
            throw new NullPointerException();
        }
        Student s2 = new Student();
        s2.setId(2);
        s2.setFirstName("2");
        s2.setLastName("2");
        s2.setAge(2);
        s2.setEmail("2");
        s2.getCollageName("2");

        this.studentDao.insert(s2);
    }

    @GetMapping("/insertMultiple2")
    public void insertMultiple2() {
        this.transactionTemplate.execute(txStatus -> {
            Student s1 = new Student();
            s1.setId(1);
            s1.setFirstName("1");
            s1.setLastName("1");
            s1.setAge(1);
            s1.setEmail("1");
            s1.getCollageName("1");

            this.studentDao.insert(s1);

            if (true) {
                throw new NullPointerException();
            }
            Student s2 = new Student();
            s2.setId(2);
            s2.setFirstName("2");
            s2.setLastName("2");
            s2.setAge(2);
            s2.setEmail("2");
            s2.getCollageName("2");

            this.studentDao.insert(s2);

            return null;
        });
    }

    @GetMapping("/insertMultiple3")
    public void insertMultiple3() {

        this.dsl.transaction(txConf -> {
            StudentDao txEd = new StudentDao(txConf);

            Student s1 = new Student();
            s1.setId(1);
            s1.setFirstName("1");
            s1.setLastName("1");
            s1.setAge(1);
            s1.setEmail("1");
            s1.getCollageName("1");

            txEd.insert(s1);

            int count = DSL.using(txConf).fetchCount(STUDENT);
            System.out.println(count);

            if(true){
                throw new NullPointerException();
            }

            Student s2 = new Student();
            s2.setId(2);
            s2.setFirstName("2");
            s2.setLastName("2");
            s2.setAge(2);
            s2.setEmail("2");
            s2.getCollageName("2");

            txEd.insert(s2);
        });
    }
}
