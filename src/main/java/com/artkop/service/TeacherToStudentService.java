package com.artkop.service;

import com.artkop.DTO.TeacherToStudentDTO;
import com.artkop.model.Student;
import com.artkop.model.Teacher;
import com.artkop.model.TeacherToStudent;
import com.artkop.repository.StudentRepository;
import com.artkop.repository.TeacherRepository;
import com.artkop.repository.TeacherToStudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherToStudentService {

    private TeacherToStudentRepository repository;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    public void addStudentToTeacher(TeacherToStudentDTO teacherToStudentDTO){
        TeacherToStudent teacherToStudent = new TeacherToStudent();
        teacherToStudent.setStudent(studentRepository.getOne(teacherToStudentDTO.getStudentId()));
        teacherToStudent.setTeacher(teacherRepository.getOne(teacherToStudentDTO.getTeacherId()));
        repository.save(teacherToStudent);
    }

    public void removeStudentFromTeacher(TeacherToStudentDTO teacherToStudentDTO){
        TeacherToStudent teacherToStudent = repository
                .findByTeacherIdAndStudentId(teacherToStudentDTO.getTeacherId(), teacherToStudentDTO.getStudentId());
        repository.deleteById(teacherToStudent.getId());
    }

    public List<Student> getStudentsForTeacher(Long id){
        List<Student> students = new ArrayList<>();
        for (TeacherToStudent tts:
                repository.getAllByTeacherId(id)) {

        students.add(tts.getStudent());
        }
    return students;
    }

    public List<Teacher> getTeachersForStudent(Long id){
        List<Teacher> teachers = new ArrayList<>();
        for (TeacherToStudent tts:
                repository.getAllByStudentId(id)) {

        teachers.add(tts.getTeacher());
        }
    return teachers;
    }


}
