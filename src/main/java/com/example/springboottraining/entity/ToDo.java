package com.example.springboottraining.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "to_do")
public class ToDo {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "taskname", nullable = false)
    private String taskname;

//    @Column(name = "description")
    private String description;

////    @Column(name = "duedate")
//    private Date dueDate;

//    @Column(name = "compleated")
    private Boolean completed;

}
