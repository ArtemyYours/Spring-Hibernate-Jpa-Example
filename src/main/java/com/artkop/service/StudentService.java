package com.artkop.service;

import com.artkop.DTO.StudentDTO;
import com.artkop.model.Student;
import com.artkop.repository.SpecializationRepo;
import com.artkop.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository repository;
    private SpecializationRepo specializationRepo;

    public void save(StudentDTO student){

        repository.save(createStudent(student));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Student> getAll(){
        return repository.findAll();
    }

    public void update(Long id, StudentDTO updatedStudent){
        Student studentToUpdate = repository.getOne(id);
        getFieldsToUpdate(updatedStudent, studentToUpdate);
        repository.save(studentToUpdate);


    }

    private void getFieldsToUpdate(StudentDTO updatedStudent, Student studentToUpdate){

        if(updatedStudent.getFirstName()!= null){
            studentToUpdate.setFirstName(updatedStudent.getFirstName());
        }
        if(updatedStudent.getLastName()!= null){
            studentToUpdate.setLastName(updatedStudent.getLastName());
        }
        if(updatedStudent.getPatronymicName() != null){
            studentToUpdate.setPatronymicName(updatedStudent.getPatronymicName());
        }
        if(updatedStudent.getLevel() != null){
            studentToUpdate.setLevel(updatedStudent.getLevel());
        }
        if(updatedStudent.getSpecialization() != null){
            studentToUpdate.setSpecialization(specializationRepo.getOne(updatedStudent.getSpecialization()));
        }

    }

    private Student createStudent(StudentDTO studentDTO){
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setPatronymicName(studentDTO.getPatronymicName());
        student.setLevel(studentDTO.getLevel());
        student.setSpecialization(specializationRepo.getOne(studentDTO.getSpecialization()));
        return student;
    }
}
