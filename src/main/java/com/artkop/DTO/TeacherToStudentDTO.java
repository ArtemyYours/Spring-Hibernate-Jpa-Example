package com.artkop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeacherToStudentDTO {

    private Long teacherId;

    private Long studentId;
}
