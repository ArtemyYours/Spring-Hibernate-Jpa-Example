package com.artkop.DTO;

import com.artkop.model.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeacherDTO {

    private String firstName;

    private String lastName;

    private String patronymicName;

    private Long department;
}
