package com.techprime.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_theme")  
    private String projectTheme;    

    private String reason;
    private String type;
    private String division;
    private String category;
    private String priority;
    private String department;

    @Temporal(TemporalType.DATE)  
    private Date startDate;

    @Temporal(TemporalType.DATE)  
    private Date endDate;

    private String location;
    private String status = "Registered";
}
