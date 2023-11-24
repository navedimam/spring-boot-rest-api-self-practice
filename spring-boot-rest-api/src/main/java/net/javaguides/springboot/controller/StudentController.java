package net.javaguides.springboot.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"Naved","Imam");
       // return student;
       // return new ResponseEntity<>(student,HttpStatus.OK);
       // return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("Custom-Header","Rahul").body(student);
    }
    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>>getStudents(){
        List<Student> student = new ArrayList<>();
        student.add(new Student(02,"Shahruk","Khan"));
        student.add(new Student(03,"Salman","Khan"));
        student.add(new Student(04,"Amir","Khan"));
        student.add(new Student(05,"Fardin","Khan"));
       // return student;
        return ResponseEntity.ok(student);
    }


    // SpringBoot REST API with PathVariable
    // {id}- URI Template Variable
    // http://localhost:8080/students/2/naved/imam
    @GetMapping ("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
            Student student=new Student(studentId,firstName,lastName);
       // return new Student(studentId,firstName,lastName);
            return ResponseEntity.ok(student);

    }

    // Spring boot REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Naved&lastName=Imam

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student=new Student(id,firstName,lastName);
       // return new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);

    }

    // Spring boot REST API that handles HTTP POST Request - creating new Resource
    // @PostMapping & @RequestBody

    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
       // return student;
        return new ResponseEntity<>(student,HttpStatus.CREATED);

    }

    // SpringBoot REST API that handles HTTP PUT Request-updating existing resource

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){

        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        //return student;
        return ResponseEntity.ok(student);

    }

    // SpringBoot REST API that handles HTTP DELETE Request - deleting the existing resource

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deletestudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
       // return "Student Deleted Successfully!";
        return ResponseEntity.ok("Student Deleted Successfully!");

    }

}
