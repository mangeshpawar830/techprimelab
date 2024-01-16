package com.techprime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techprime.DTO.DashboardChartData;
import com.techprime.DTO.ProjectInfoResponse;
import com.techprime.model.Project;
import com.techprime.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	 @PostMapping("/create")
	    public ResponseEntity<String> createProject(@RequestBody Project project) {
	        projectService.createProject(project);
	        return ResponseEntity.ok("Project created successfully");
	    }
	 
	 @PostMapping("/status/{id}/{status}")
	 public ResponseEntity<Project> updateProjectStatus(@PathVariable Long id, @PathVariable String status) {
	     System.out.println("Received request to update project status. ID: " + id + ", Status: " + status);

	     Project updatedProject = projectService.updateProjectStatus(id, status);
	     return ResponseEntity.ok(updatedProject);
	 }

	    
	    @GetMapping("/{id}")
	    public Project getProjectById(@PathVariable Long id) {
	        return projectService.getProjectById(id);
	    }
	 	
	 	 @GetMapping("/projectinfo")
	     public ResponseEntity<ProjectInfoResponse> getProjectInfo() {
	         ProjectInfoResponse response = projectService.getProjectInfo();
	         return ResponseEntity.ok(response);
	     }
	 	 
	 	 @GetMapping("/dashboardchart")
	     public List<DashboardChartData> getDashboardChart() {
	         return projectService.getDashboardChart(); 
	     }

	 	 
	 	 @GetMapping("/list")
	     public ResponseEntity<List<Project>> getAllProjects(@RequestParam(required = false) String search,
	                                                        @RequestParam(defaultValue = "1") int page,
	                                                        @RequestParam(required = false) String sort) {
	         List<Project> projects = projectService.getAllProjects(search, page, sort);
	         return ResponseEntity.ok(projects);
	     }
	 	 
	 	
	 	 
	 	

}
