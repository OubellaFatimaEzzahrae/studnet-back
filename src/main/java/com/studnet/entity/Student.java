package com.studnet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String birthday;

    @ManyToOne
    @JoinColumn(name = "major_id")
    @JsonBackReference
    private Major major;
}
