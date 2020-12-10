package com.artkop.controller;

import com.artkop.DTO.StudentDTO;
import com.artkop.DTO.TeacherDTO;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.service.StudentService;
import com.artkop.service.TeacherService;
import com.artkop.service.TeacherToStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/students")
@AllArgsConstructor
public class StudentController {

    StudentService service;
    TeacherToStudentService teacherToStudentService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        return service.getAll();
    }

    @GetMapping("/getTeachersForStudent/{id}")
    @ResponseBody
    public List<Teacher> getStudentsForTeacher(@PathVariable long id){
        return teacherToStudentService.getTeachersForStudent(id);
    }

    @PostMapping("/create")
    public void newTeacher(@RequestBody StudentDTO studentDTO){
        service.save(studentDTO);
    }

    @DeleteMapping("/deleteStudentr")
    public void deleteTeacher(@RequestBody Long id){
        service.delete(id);
    }

    @PutMapping (value = "/updateStudent/{id})")
    public void updateTeacher(@PathVariable long id, @RequestBody StudentDTO studentDTO){
        service.update(id, studentDTO);
    }
}
