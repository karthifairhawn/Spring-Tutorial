package com.sample.jpa.spring.boot.ample.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (
        name = "student_tbl",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email_address"
                )
    )
public class Student {

    @Id
    @SequenceGenerator(
            name="StudentID_Seq",
            sequenceName="Student_Seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Student_Seq"
    )
    private long StudentID;

    private String firstName;
    private String lastName;

    @Column(
            name="email_address",
            nullable = false
            )
    private String emailId;

    @Embedded
    private Guardian guardian;
}
