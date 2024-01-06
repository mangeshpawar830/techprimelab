package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.ProjectCatogory;
import com.project.model.ProjectDept;

import java.io.Serializable;

@Repository
public interface ProjectDeptRepository extends JpaRepository<ProjectDept, Serializable> {
}
