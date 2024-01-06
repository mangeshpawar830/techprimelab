package com.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectDept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdid")
    private int pdid;
    private String pdname;
    
}
