package com.artkop.controller;

import com.artkop.DTO.TeacherDTO;
import com.artkop.configuration.RabbitMq.RabbitMQSettings;
import com.artkop.model.Message;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.service.TeacherService;
import com.artkop.service.TeacherToStudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/simplaccess/teachers")
@AllArgsConstructor
@Slf4j
public class TeacherController {

    TeacherService service;
    TeacherToStudentService teacherToStudentService;
    RabbitTemplate rabbitTemplate;
    RabbitMQSettings rabbitMQSettings;

    @ApiOperation(value = "Get list of all students")
    @GetMapping("/getAll")
    @ResponseBody
    public List<Teacher> getAll(){
        Message message = new Message();
        message.setMessage("Teachers get all method has been called");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        List<Teacher> teachers = service.getAll();
        log.info(teachers.toString());
        return teachers;
    }

    @ApiOperation(value = "Get all students, who is being taught by this teacher")
    @GetMapping("/getStudentsForTeacher/{id}")
    @ResponseBody
    public List<Student> getStudentsForTeacher(@PathVariable long id){
        Message message = new Message();
        message.setMessage("Teachers get all students has been called");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        return teacherToStudentService.getStudentsForTeacher(id);
    }

    @ApiOperation(value = "Create new teacher")
    @PostMapping("/create")
    public void newTeacher(@RequestBody TeacherDTO teacher){
        Message message = new Message();
        message.setMessage("Teacher has been created");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.save(teacher);
    }

    @ApiOperation(value = "Delete teacher with input id")
    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody Long id){
        Message message = new Message();
        message.setMessage("Teacher has been deleted");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.delete(id);
    }

    @ApiOperation(value = "Update teacher parameters")
    @PutMapping (value = "/updateTeacher/{id}")
    public void updateTeacher(@PathVariable long id, @RequestBody TeacherDTO teacher){
        Message message = new Message();
        message.setMessage("Teacher has been dedited");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.update(id, teacher);
    }
}
