package com.project.repository;

import jakarta.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.ProjectCatogory;
import com.project.model.ProjectInfo;

import java.io.Serializable;
import java.util.List;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Serializable> {
    List<ProjectInfo> findByPronameContainingIgnoreCase(String projectName);

    List<ProjectInfo> findByProjectTypePtid(int ptid);

    List<ProjectInfo> findByProjectReasonPrid(int prid);
    
    
    
    @Query("SELECT pd.pdname, " +
    	       "       SUM(CASE WHEN ps.psname = 'Closed' THEN 1 ELSE 0 END) as closedProjects, " +
    	       "       COUNT(*) as totalProjects " +
    	       "FROM ProjectInfo pi " +
    	       "JOIN pi.projectDept pd " +
    	       "JOIN pi.projectStatus ps " +
    	       "GROUP BY pd.pdname")
    	List<Object> getDepartmentProjectSummary();
   
    @Query("SELECT count(p) FROM ProjectInfo p WHERE p.projectStatus.psname = 'Running' AND p.proenddate < CURRENT_DATE")
    int countDelayedRunningProjects();
}