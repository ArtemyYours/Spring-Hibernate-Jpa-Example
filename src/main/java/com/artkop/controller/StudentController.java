package com.artkop.controller;

import com.artkop.DTO.StudentDTO;
import com.artkop.configuration.RabbitMq.RabbitMQSettings;
import com.artkop.model.Message;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.service.RabbitSenderService;
import com.artkop.service.StudentService;
import com.artkop.service.TeacherToStudentService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/simpleaccess/students")
@AllArgsConstructor
public class StudentController {

    StudentService service;
    TeacherToStudentService teacherToStudentService;
    RabbitSenderService sender;

    @ApiOperation(value = "View list of all students")
    @GetMapping("/getAll")
    @ResponseBody
    public List<Student> getAll(){
        sender.sendMessageToRabbit("Students get all method got called");
        return service.getAll();
    }

    @ApiOperation(value = "View list of all teachers, who work with input student")
    @GetMapping("/getTeachersForStudent/{id}")
    @ResponseBody
    public List<Teacher> getTeachersForStudent(@PathVariable long id){
        sender.sendMessageToRabbit("Students get Teachers  method got called");
        return teacherToStudentService.getTeachersForStudent(id);
    }

    @ApiOperation(value = "Create new student")
    @PostMapping("/create")
    public void newStudent(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String patronymicName,
                           @RequestParam Long specializationId,
                           @RequestParam Integer level
    ){
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setFirstName(firstName);
        studentDTO.setLastName(lastName);
        studentDTO.setPatronymicName(patronymicName);
        studentDTO.setSpecialization(specializationId);
        studentDTO.setLevel(level);

        sender.sendMessageToRabbit("Student has been created");
        service.save(studentDTO);
    }

    @ApiOperation(value = "Delete student with input idt")
    @DeleteMapping("/deleteStudentr")
    public void deleteStudent(@RequestBody Long id){
        sender.sendMessageToRabbit("Student has been deleted");
        service.delete(id);
    }

    @ApiOperation(value = "update student fields")
    @PutMapping (value = "/updateStudent/{id})")
    public void updateStudent(@RequestParam @NotNull long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String patronymicName,
                              @RequestParam(required = false) Long specializationId,
                              @RequestParam(required = false) Integer level
                              ){
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setFirstName(firstName);
        studentDTO.setLastName(lastName);
        studentDTO.setPatronymicName(patronymicName);
        studentDTO.setSpecialization(specializationId);
        studentDTO.setLevel(level);

        sender.sendMessageToRabbit("Student has been edited");
        service.update(id, studentDTO);
    }
}
