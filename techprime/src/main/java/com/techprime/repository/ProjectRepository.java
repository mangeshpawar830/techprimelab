package com.techprime.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techprime.DTO.DashboardChartData;
import com.techprime.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	long countByStatus(String status);
	
    	Page<Project> findAll(Pageable pageable);

	
		@Query("SELECT COUNT(p) FROM Project p WHERE p.status = 'Running' AND p.endDate < CURRENT_DATE")
	    long countDelayedRunningProjects();
		
		List<Project> findByStatus(String status);
		
		@Query(value = "SELECT NEW com.techprime.DTO.DashboardChartData(" +
		        "department, " +
		        "COUNT(*), " +
		        "SUM(CASE WHEN status = 'Closed' THEN 1 ELSE 0 END), " +
		        "100.0 * SUM(CASE WHEN status = 'Closed' THEN 1 ELSE 0 END) / COUNT(*)) " +
		        "FROM Project " +
		        "GROUP BY department")
		List<DashboardChartData> getDashboardChartData();


}
