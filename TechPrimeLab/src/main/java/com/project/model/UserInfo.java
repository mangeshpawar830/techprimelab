package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserInfo {
    @Id
    private String uemail;
    private String upass;


}

