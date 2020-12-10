package com.artkop.controller;

import com.artkop.DTO.TeacherDTO;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.model.TeacherToStudent;
import com.artkop.service.TeacherService;
import com.artkop.service.TeacherToStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/teachers")
@AllArgsConstructor
public class TeacherController {

    TeacherService service;
    TeacherToStudentService teacherToStudentService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Teacher> getAll(){
        return service.getAll();
    }

    @GetMapping("/getStudentsForTeacher/{id}")
    @ResponseBody
    public List<Student> getStudentsForTeacher(@PathVariable long id){
        return teacherToStudentService.getStudentsForTeacher(id);
    }

    @PostMapping("/create")
    public void newTeacher(@RequestBody TeacherDTO teacher){
        service.save(teacher);
    }

    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody Long id){
        service.delete(id);
    }

    @PutMapping (value = "/updateTeacher/{id}")
    public void updateTeacher(@PathVariable long id, @RequestBody TeacherDTO teacher){
        service.update(id, teacher);
    }
}
