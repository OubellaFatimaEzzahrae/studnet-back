package com.studnet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //(@Getter, @Setter, @ToString, @EqualsAndHashCode, et @RequiredArgsConstructor.)

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String birthday;

    @ManyToOne
    @JoinColumn(name = "course_id") // FK vers Course
    private Course course; // chaque étudiant n'a qu'un seul cours
}
