package com.artkop.controller;

import com.artkop.DTO.StudentDTO;
import com.artkop.DTO.TeacherDTO;
import com.artkop.configuration.RabbitMQConfig;
import com.artkop.configuration.settings.RabbitMQSettings;
import com.artkop.model.Message;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.service.StudentService;
import com.artkop.service.TeacherService;
import com.artkop.service.TeacherToStudentService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/students")
@AllArgsConstructor
public class StudentController {

    StudentService service;
    TeacherToStudentService teacherToStudentService;
    RabbitTemplate rabbitTemplate;
    RabbitMQSettings rabbitMQSettings;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        Message message = new Message();
        message.setMessage("Students get all method got called");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        return service.getAll();
    }

    @GetMapping("/getTeachersForStudent/{id}")
    @ResponseBody
    public List<Teacher> getTeachersForStudent(@PathVariable long id){
        Message message = new Message();
        message.setMessage("Students get Teachers  method got called");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        return teacherToStudentService.getTeachersForStudent(id);
    }

    @PostMapping("/create")
    public void newTeacher(@RequestBody StudentDTO studentDTO){

        Message message = new Message();
        message.setMessage("Student has been created");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.save(studentDTO);
    }

    @DeleteMapping("/deleteStudentr")
    public void deleteTeacher(@RequestBody Long id){
        Message message = new Message();
        message.setMessage("Student has been deleted");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.delete(id);
    }

    @PutMapping (value = "/updateStudent/{id})")
    public void updateTeacher(@PathVariable long id, @RequestBody StudentDTO studentDTO){

        Message message = new Message();
        message.setMessage("Student has been edited");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.update(id, studentDTO);
    }
}
