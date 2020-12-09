package com.artkop.controller;

import com.artkop.DTO.TeacherDTO;
import com.artkop.model.Teacher;
import com.artkop.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/teachers")
@AllArgsConstructor
public class TeacherController {

    TeacherService service;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Teacher> getAll(){
        return service.getAll();
    }

    @PostMapping("/create")
    public void newTeacher(@RequestBody TeacherDTO teacher){
        System.out.println(teacher);
        service.save(teacher);
    }

    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody Long id){
        service.delete(id);
    }

    @PutMapping (value = "/updateTeacher")
    public void updateTeacher(@RequestBody Long id, @RequestBody TeacherDTO teacher){

        service.update(id, teacher);
    }
}
