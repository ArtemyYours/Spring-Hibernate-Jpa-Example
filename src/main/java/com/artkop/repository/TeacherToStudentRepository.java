package com.artkop.repository;

import com.artkop.model.TeacherToStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherToStudentRepository extends JpaRepository<TeacherToStudent, Long> {

    TeacherToStudent findByTeacherIdAndStudentId(Long teacherId, Long studentId);

    List<TeacherToStudent> getAllByTeacherId(Long teacherId);

    List<TeacherToStudent> getAllByStudentId(Long studentId);
}
