package com.artkop.controller;

import com.artkop.DTO.TeacherDTO;
import com.artkop.DTO.TeacherToStudentDTO;
import com.artkop.service.TeacherToStudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/relation")
@AllArgsConstructor
public class StudentToTeacherRelationController {
    TeacherToStudentService service;

    @PostMapping("/addStudentToTeacher/{teacherId})/{StudentId})")
    public void newTeachertoStudent(@PathVariable long teacherId, @PathVariable long studentId){
        TeacherToStudentDTO teacherToStudentDTO = new TeacherToStudentDTO(teacherId, studentId);
        service.addStudentToTeacher(teacherToStudentDTO);
    }

    @PostMapping("/removeStudentFromTeacher/{teacherId})/{StudentId})")
    public void ndeleteStudentFromTeacher(@PathVariable long teacherId, @PathVariable long studentId){
        TeacherToStudentDTO teacherToStudentDTO = new TeacherToStudentDTO(teacherId, studentId);
        service.removeStudentFromTeacher(teacherToStudentDTO);
    }

}
