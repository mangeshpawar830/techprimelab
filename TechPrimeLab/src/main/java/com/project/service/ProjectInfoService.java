package com.project.service;

import com.project.model.ProjectInfo;
import com.project.repository.ProjectInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectInfoService {

    private final ProjectInfoRepository projectInfoRepository;

    @Autowired
    public ProjectInfoService(ProjectInfoRepository projectInfoRepository) {
        this.projectInfoRepository = projectInfoRepository;
    }

    public List<ProjectInfo> getAllProjectInfos() {
        return projectInfoRepository.findAll();
    }

    public Optional<ProjectInfo> getProjectInfoById(int proid) {
        return projectInfoRepository.findById(proid);
    }

    public ProjectInfo saveProjectInfo(ProjectInfo projectInfo) {
        return projectInfoRepository.save(projectInfo);
    }

    public void deleteProjectInfo(int proid) {
        projectInfoRepository.deleteById(proid);
    }

    // Additional methods for handling associations

    public List<ProjectInfo> getProjectInfosByReason(int prid) {
        return projectInfoRepository.findByProjectReasonPrid(prid);
    }

    public List<ProjectInfo> getProjectInfosByType(int ptid) {
        return projectInfoRepository.findByProjectTypePtid(ptid);
    }

    // Search method
    public List<ProjectInfo> searchProjectsByName(String projectName) {
        return projectInfoRepository.findByPronameContainingIgnoreCase(projectName);
    }

    // Sorting methods

    public List<ProjectInfo> getAllProjectInfosSortedByName() {
        return projectInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "proname"));
    }

    public List<ProjectInfo> getAllProjectInfosSortedByStartDate() {
        return projectInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "prostartdate"));
    }

    public List<ProjectInfo> getAllProjectInfosSortedByEndDate() {
        return projectInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "proenddate"));
    }

    public List<Object> getDepartmentProjectSummary() {
        return projectInfoRepository.getDepartmentProjectSummary();
    }
}
