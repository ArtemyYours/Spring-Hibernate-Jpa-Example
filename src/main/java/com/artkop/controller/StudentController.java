package com.artkop.controller;

import com.artkop.DTO.StudentDTO;
import com.artkop.configuration.RabbitMq.RabbitMQSettings;
import com.artkop.model.Message;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.service.StudentService;
import com.artkop.service.TeacherToStudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/simplaccess/students")
@AllArgsConstructor
public class StudentController {

    StudentService service;
    TeacherToStudentService teacherToStudentService;
    RabbitTemplate rabbitTemplate;
    RabbitMQSettings rabbitMQSettings;

    @ApiOperation(value = "View list of all students")
    @GetMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        Message message = new Message();
        message.setMessage("Students get all method got called");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        return service.getAll();
    }

    @ApiOperation(value = "View list of all teachers, who work with input student")
    @GetMapping("/getTeachersForStudent/{id}")
    @ResponseBody
    public List<Teacher> getTeachersForStudent(@PathVariable long id){
        Message message = new Message();
        message.setMessage("Students get Teachers  method got called");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        return teacherToStudentService.getTeachersForStudent(id);
    }

    @ApiOperation(value = "Create new student")
    @PostMapping("/create")
    public void newStudent(@RequestBody StudentDTO studentDTO){

        Message message = new Message();
        message.setMessage("Student has been created");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.save(studentDTO);
    }

    @ApiOperation(value = "Delete student with input idt")
    @DeleteMapping("/deleteStudentr")
    public void deleteTeacher(@RequestBody Long id){
        Message message = new Message();
        message.setMessage("Student has been deleted");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.delete(id);
    }

    @ApiOperation(value = "update student fields")
    @PutMapping (value = "/updateStudent/{id})")
    public void updateTeacher(@PathVariable long id, @RequestBody StudentDTO studentDTO){

        Message message = new Message();
        message.setMessage("Student has been edited");
        rabbitTemplate
                .convertAndSend(rabbitMQSettings.getExchange(), rabbitMQSettings.getRoutingKey(), message);
        service.update(id, studentDTO);
    }
}
