package com.artkop.controller;

import com.artkop.DTO.TeacherToStudentDTO;
import com.artkop.configuration.RabbitMq.RabbitMQSettings;
import com.artkop.model.Message;
import com.artkop.service.TeacherToStudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/advancedaccess/relation")
@AllArgsConstructor
public class StudentToTeacherRelationController {
    TeacherToStudentService service;
    RabbitTemplate rabbitTemplate;
    RabbitMQSettings rabbitMQSettings;

    @ApiOperation(value = "Add new Teacher to Student relation")
    @PostMapping("/addStudentToTeacher/{teacherId})/{StudentId})")
    public void newTeachertoStudent(@PathVariable long teacherId, @PathVariable long studentId){
        Message message = new Message();
        message.setMessage("new Teacher-ToStudent relation has been added");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        TeacherToStudentDTO teacherToStudentDTO = new TeacherToStudentDTO(teacherId, studentId);
        service.addStudentToTeacher(teacherToStudentDTO);
    }

    @ApiOperation(value = "Delete Teacher to Student relation")
    @DeleteMapping("/removeStudentFromTeacher/{teacherId})/{StudentId})")
    public void deleteStudentFromTeacher(@PathVariable long teacherId, @PathVariable long studentId){

        Message message = new Message();
        message.setMessage("Teacher-ToStudent relation has been deleted");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        TeacherToStudentDTO teacherToStudentDTO = new TeacherToStudentDTO(teacherId, studentId);
        service.removeStudentFromTeacher(teacherToStudentDTO);
    }

}
