package com.artkop.controller;

import com.artkop.DTO.StudentDTO;
import com.artkop.DTO.TeacherDTO;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.service.StudentService;
import com.artkop.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/students")
@AllArgsConstructor
public class StudentController {

    StudentService service;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        return service.getAll();
    }

    @PostMapping("/create")
    public void newTeacher(@RequestBody StudentDTO studentDTO){
        service.save(studentDTO);
    }

    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody Long id){
        service.delete(id);
    }

    @PutMapping (value = "/updateTeacher")
    public void updateTeacher(@RequestBody Long id, @RequestBody StudentDTO studentDTO){

        service.update(id, studentDTO);
    }
}
