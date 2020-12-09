package com.artkop.service;

import com.artkop.DTO.TeacherDTO;
import com.artkop.model.Department;
import com.artkop.model.Teacher;
import com.artkop.repository.DepartmentRepository;
import com.artkop.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private TeacherRepository repository;
    private DepartmentRepository departmentRepository;

    public void save(TeacherDTO teacher){
        repository.save(createTeacher(teacher));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Teacher> getAll(){
        return repository.findAll();
    }

    public void update(Long id, TeacherDTO updatedTeacher){
        Teacher teacherToUpdate = repository.getOne(id);

        getNewFields(createTeacher(updatedTeacher), teacherToUpdate);
        repository.save(teacherToUpdate);
    }

    private void getNewFields(Teacher updatedTeacher, Teacher teacherToUpdate){
        if(updatedTeacher.getDepartment()!=null){
            teacherToUpdate.setDepartment(updatedTeacher.getDepartment());
        }
        if(updatedTeacher.getFirstName()!=null){
            teacherToUpdate.setFirstName(updatedTeacher.getFirstName());
        }
        if(updatedTeacher.getLastName()!=null){
            teacherToUpdate.setLastName(updatedTeacher.getLastName());
        }
        if(updatedTeacher.getPatronymicName()!=null){
            teacherToUpdate.setPatronymicName(updatedTeacher.getPatronymicName());
        }

    }
    private Teacher createTeacher(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setPatronymicName(teacherDTO.getPatronymicName());
        teacher.setDepartment(departmentRepository.getOne(teacherDTO.getDepartment()));
        return teacher;
    }


}
