package com.artkop.DTO;

import com.artkop.model.Specialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class StudentDTO {
    private String firstName;

    private String lastName;

    private String patronymicName;

    private Long specialization;

    private Integer level;
}
