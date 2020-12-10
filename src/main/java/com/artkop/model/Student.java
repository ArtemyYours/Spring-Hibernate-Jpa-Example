package com.artkop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String patronymicName;

    @ManyToOne
    @JoinColumn(name="specialization_id", nullable=false)
    private Specialization specialization;


    @OneToMany(mappedBy="student")
    @JsonIgnore
    private List <TeacherToStudent> teacherToStudents;

    private Integer level;


}
