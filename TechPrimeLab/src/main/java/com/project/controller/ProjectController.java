package com.project.controller;

import com.project.dto.ProjectDTO;
import com.project.model.ProjectCatogory;
import com.project.model.ProjectDept;
import com.project.model.ProjectDivision;
import com.project.model.ProjectInfo;
import com.project.model.ProjectLocation;
import com.project.model.ProjectPriority;
import com.project.model.ProjectReason;
import com.project.model.ProjectStatus;
import com.project.model.ProjectType;
import com.project.repository.ProjectInfoRepository;
import com.project.service.ProjectCatagoryService;
import com.project.service.ProjectDeptService;
import com.project.service.ProjectDivisionService;
import com.project.service.ProjectInfoService;
import com.project.service.ProjectLocationService;
import com.project.service.ProjectPriorityService;
import com.project.service.ProjectReasonService;
import com.project.service.ProjectStatusService;
import com.project.service.ProjectTypeService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/projectcatogory")
public class ProjectController {

	@Autowired
	private ProjectCatagoryService Cservice;

	@Autowired
	private ProjectInfoService pservice;

	@Autowired
	private ProjectReasonService reasonService; 

	@Autowired
	private ProjectTypeService typeService;

	@Autowired
	private ProjectDivisionService divisionService;

	@Autowired
	private ProjectPriorityService priorityService;

	@Autowired
	private ProjectDeptService deptService;

	@Autowired
	private ProjectLocationService locationService;

	@Autowired
	private ProjectStatusService statusService;
	
	@Autowired
	private ProjectInfoRepository prrepo;

	@PostMapping("/save")
	public ProjectInfo getProjectCatogory(@RequestBody ProjectDTO data) {

		ProjectInfo pinfo = new ProjectInfo();
		pinfo.setProname(data.getProjectName());
		pinfo.setProstartdate(data.getProjectStartDate());
		pinfo.setProenddate(data.getProjectEndDate());

		ProjectReason pres = new ProjectReason();
		pres.setPrname(data.getReasonName());
		pinfo.setProjectReason(pres);
		reasonService.saveProjectReason(pres);

		ProjectType ptype = new ProjectType();
		ptype.setPtname(data.getTypeName());
		pinfo.setProjectType(ptype);
		typeService.saveProjectType(ptype);

		ProjectDivision pdev = new ProjectDivision();
		pdev.setPdivname(data.getDivisionName());
		pinfo.setProjectDivision(pdev);
		divisionService.saveProjectDivision(pdev);

		ProjectCatogory pc = new ProjectCatogory();
		pc.setPcname(data.getCategoryName());
		pinfo.setProjectCategory(pc);
		Cservice.saveProjectCategory(pc);

		ProjectPriority prio = new ProjectPriority();
		prio.setPpname(data.getPriorityName());
		pinfo.setProjectPriority(prio);
		priorityService.saveProjectPriority(prio);

		ProjectDept pd = new ProjectDept();
		pd.setPdname(data.getDeptName());
		pinfo.setProjectDept(pd);
		deptService.saveProjectDept(pd);

		ProjectLocation ploc = new ProjectLocation();
		ploc.setPlname(data.getLocationName());
		pinfo.setProjectLocation(ploc);

		ProjectStatus pstatus = new ProjectStatus();
		pstatus.setPsname(data.getStatusName());
		pinfo.setProjectStatus(pstatus);
		locationService.saveProjectLocation(ploc);
		statusService.saveProjectStatus(pstatus);

		return pservice.saveProjectInfo(pinfo);
	}

	@GetMapping("/getAllinfo")
	public List<ProjectInfo> getAllProjectInfos() {
		return pservice.getAllProjectInfos();
	}

	@PostMapping("/changeStatus")
	@Transactional
	public List<ProjectInfo> change_status(@RequestBody Map<String, String> data) {

		String status = data.get("psname");
		int projectId = Integer.parseInt(data.get("psid"));
		System.out.println(projectId);
		System.out.println(status);
		statusService.changeStatus(status, projectId);

		return pservice.getAllProjectInfos();
	}

	@GetMapping("/projectcount")
	public Map<String, String> getCount() {

		Map<String, String> countMap = new HashMap<String, String>();

		int totalProjects = statusService.getProjectCountByPsid();
		int totalRunningProject = statusService.getProjectCountByPsname("Running");
		int totalClosedProject = statusService.getProjectCountByPsname("Closed");
		int totalCancelledProject = statusService.getProjectCountByPsname("Cancelled");
		int totalRegisteredProject = statusService.getProjectCountByPsname("Registerd");
		int delayedcountProject=prrepo.countDelayedRunningProjects();

		countMap.put("TotalProjects", String.valueOf(totalProjects));
		countMap.put("TotalRunningProject", String.valueOf(totalRunningProject));
		countMap.put("TotalClosedProject", String.valueOf(totalClosedProject));
		countMap.put("TotalCancelledProject", String.valueOf(totalCancelledProject));
		countMap.put("TotalRegisteredProject", String.valueOf(totalRegisteredProject));
		countMap.put("TotalDelayedProject", String.valueOf(delayedcountProject));
		
		return countMap;
	}

	@GetMapping("/summary")
	public ResponseEntity<List<Object>> getDepartmentProjectSummary() {
		

		return new ResponseEntity<>(pservice.getDepartmentProjectSummary(),HttpStatus.OK);
	}

}
