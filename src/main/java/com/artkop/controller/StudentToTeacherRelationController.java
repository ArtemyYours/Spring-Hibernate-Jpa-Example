package com.artkop.controller;

import com.artkop.DTO.TeacherToStudentDTO;
import com.artkop.service.RabbitSenderService;
import com.artkop.service.TeacherToStudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/advancedaccess/relation")
@AllArgsConstructor
public class StudentToTeacherRelationController {
    TeacherToStudentService service;
    RabbitSenderService sender;

    @ApiOperation(value = "Add new Teacher to Student relation")
    @PostMapping("/addStudentToTeacher/")
    public void newTeachertoStudent(@RequestParam long teacherId, @RequestParam long studentId){
        sender.sendMessageToRabbit("new Teacher-ToStudent relation has been added");
        TeacherToStudentDTO teacherToStudentDTO = new TeacherToStudentDTO(teacherId, studentId);
        service.addStudentToTeacher(teacherToStudentDTO);
    }

    @ApiOperation(value = "Delete Teacher to Student relation")
    @DeleteMapping("/removeStudentFromTeacher/)")
    public void deleteStudentFromTeacher(@RequestParam long teacherId, @RequestParam long studentId){
        sender.sendMessageToRabbit("Teacher-ToStudent relation has been deleted");
        TeacherToStudentDTO teacherToStudentDTO = new TeacherToStudentDTO(teacherId, studentId);
        service.removeStudentFromTeacher(teacherToStudentDTO);
    }

}
