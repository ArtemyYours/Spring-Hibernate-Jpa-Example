package com.artkop.controller;

import com.artkop.DTO.TeacherDTO;
import com.artkop.configuration.RabbitMq.RabbitMQSettings;
import com.artkop.model.Message;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.service.RabbitSenderService;
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
    RabbitSenderService sender;

    @ApiOperation(value = "Get list of all students")
    @GetMapping("/getAll")
    @ResponseBody
    public List<Teacher> getAll(){
        sender.sendMessageToRabbit("Teachers get all method has been called");
        List<Teacher> teachers = service.getAll();
        log.info(teachers.toString());
        return teachers;
    }

    @ApiOperation(value = "Get all students, who is being taught by this teacher")
    @GetMapping("/getStudentsForTeacher/{id}")
    @ResponseBody
    public List<Student> getStudentsForTeacher(@PathVariable long id){
        sender.sendMessageToRabbit("Teachers get all students has been called");
        return teacherToStudentService.getStudentsForTeacher(id);
    }

    @ApiOperation(value = "Create new teacher")
    @PostMapping("/create")
    public void newTeacher(@RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) String patronymicName,
                           @RequestParam(required = false) Long department){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setFirstName(firstName);
        teacherDTO.setLastName(lastName);
        teacherDTO.setPatronymicName(patronymicName);
        teacherDTO.setDepartment(department);
        sender.sendMessageToRabbit("Teacher has been created");
        service.save(teacherDTO);
    }

    @ApiOperation(value = "Delete teacher with input id")
    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody Long id){
        sender.sendMessageToRabbit("Teacher has been deleted");
        service.delete(id);
    }

    @ApiOperation(value = "Update teacher parameters")
    @PutMapping (value = "/updateTeacher/{id}")
    public void updateTeacher(@RequestParam long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String patronymicName,
                              @RequestParam(required = false) Long department){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setFirstName(firstName);
        teacherDTO.setLastName(lastName);
        teacherDTO.setPatronymicName(patronymicName);
        teacherDTO.setDepartment(department);
        sender.sendMessageToRabbit("Teacher has been dedited");
        service.update(id, teacherDTO);
    }
}
