package com.techprime.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techprime.DTO.DashboardChartData;
import com.techprime.DTO.ProjectInfoResponse;
import com.techprime.model.Project;
import com.techprime.repository.ProjectRepository;

@Service
public class ProjectService {
	
	
	@Autowired
    private ProjectRepository projectRepository;
	
	
	public void createProject(Project project) {
        projectRepository.save(project);
        System.out.println("added successfully");
		
	}
	
	 public Project updateProjectStatus(Long id, String status) {
	        Project project = projectRepository.findById(id).orElse(null);
	        if (project != null) {
	            project.setStatus(status);
	            return projectRepository.save(project);
	        }
	        return null;
	    }
	 
	 public Project getProjectById(Long id) {
	        Optional<Project> optionalProject = projectRepository.findById(id);
	        return optionalProject.orElse(null);
	    }
	 
	 
	 
	 public ProjectInfoResponse getProjectInfo() {
	        try {
	            long totalCount = projectRepository.count();
	            long canceledCount = projectRepository.countByStatus("Cancelled");
	            long runningCount = projectRepository.countByStatus("Running");
	            long closedCount = projectRepository.countByStatus("Closed");
	            long registeredCount = projectRepository.countByStatus("Registered");
	            long delayedRunningCount = projectRepository.countDelayedRunningProjects();

	            ProjectInfoResponse response = new ProjectInfoResponse();
	            response.setTotal(totalCount);
	            response.setCancel(canceledCount);
	            response.setRunning(runningCount);
	            response.setRegistered(registeredCount);
	            response.setClosed(closedCount);
	            response.setDelayedRunning(delayedRunningCount);

	            return response;
	        } catch (Exception e) {
	            throw new RuntimeException("Unable to fetch total projects", e);
	        }
	    }
	 
	 public List<Project> getAllProjects(String search, int page, String sort) {
		    int ITEM_PER_PAGE = 7;

		    Pageable pageable;
		    if (sort != null && !sort.isEmpty()) {
		        String[] sortParams = sort.split(":");
		        String field = sortParams[0];
		        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("asc")
		                ? Sort.Direction.ASC
		                : Sort.Direction.DESC;
		        pageable = PageRequest.of(page - 1, ITEM_PER_PAGE, Sort.by(direction, field));
		    } else {
		        pageable = PageRequest.of(page - 1, ITEM_PER_PAGE);
		    }

		    Page<Project> projectsPage = projectRepository.findAll(pageable);

		    return projectsPage.getContent();
		}
	 

	    

	  
	  
	  public List<DashboardChartData> getDashboardChart() {
	        return projectRepository.getDashboardChartData();
	    }
	

	
	

}
