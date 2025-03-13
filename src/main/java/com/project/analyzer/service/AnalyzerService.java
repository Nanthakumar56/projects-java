package com.project.analyzer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.project.analyzer.dto.AnalyzedDataDto;
import com.project.analyzer.dto.DataDto;
import com.project.analyzer.dto.DocumentResponse;
import com.project.analyzer.dto.DueDto;
import com.project.analyzer.dto.TasksDto;
import com.project.analyzer.entity.AnalyzedData;
import com.project.analyzer.entity.AnalyzerTasks;
import com.project.analyzer.entity.FetchedDues;
import com.project.analyzer.entity.ProjectAnalyzer;
import com.project.analyzer.repository.AnalyzerRepository;
import com.project.analyzer.repository.DataRepository;
import com.project.analyzer.repository.DuedateRepository;
import com.project.analyzer.repository.TasksRepository;

@Service
public class AnalyzerService {
	
	@Autowired
	private AnalyzerRepository analyzerRepo;
	
	@Autowired
	private TasksRepository tasksRepo;
	
	@Autowired
	private DataRepository dataRepo;
	
	@Autowired
	private DuedateRepository duedateRepo;
	
	@Autowired
    private RestTemplate restTemplate;
	
	 private final Path fileStorageLocation = Paths.get("documents").toAbsolutePath().normalize();
	 
	 public AnalyzerService() {
	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new RuntimeException("Could not create the directory to store files.", ex);
	        }
	    }
	 
	 
	 @Transactional
	    public ProjectAnalyzer uploadDocuments(MultipartFile file, String userid) {
	        String fileName = file.getOriginalFilename();
	        long fileSize = file.getSize();

	        try {
	        	ProjectAnalyzer documents = new ProjectAnalyzer();
	            documents.setDocname("Analysis Report for "+fileName);
	            documents.setFilesize(String.valueOf(fileSize));
	            documents.setFile(file.getBytes()); 
	            documents.setCreatedby(userid);
	            documents.setCreatedat(LocalDateTime.now());

	            analyzerRepo.save(documents);

	            return documents;

	        } catch (IOException ex) {
	            throw new RuntimeException("Could not store the file " + fileName + ". Please try again!", ex);
	        }
	    }
	    
	    public List<DocumentResponse> getAllDocument() {
	        List<ProjectAnalyzer> analyzerList = analyzerRepo.findAll();
	        List<DocumentResponse> docResponse = new ArrayList<>();

	        for (ProjectAnalyzer pa : analyzerList) {
	            String createdby = getCreatedBy(pa.getCreatedby());
                String fileUrl = "http://192.168.0.132:7007/analyzer/" + pa.getReportid();  
	            List<AnalyzerTasks> analyzertasks = getReportTasks(pa.getReportid());

	            String totalTasks = String.valueOf(analyzertasks.size());
	            String addedTasks = String.valueOf(analyzertasks.stream().filter(task -> task.isIsadded()).count());
	            String notAddedTasks = String.valueOf(Integer.parseInt(totalTasks) - Integer.parseInt(addedTasks));

	            docResponse.add(new DocumentResponse(
	                pa.getReportid(),
	                pa.getDocname(),
	                totalTasks,
	                addedTasks,
	                notAddedTasks,
	                createdby,
	                pa.getCreatedat(),
	                fileUrl
	            ));
	        }
	        return docResponse;
	    }

	    public Optional<ProjectAnalyzer> getDocument(String reportid)
	    {
	    	return analyzerRepo.getByReportid(reportid);
	    }

	    private String getCreatedBy(String userId) {
	        String url = "http://192.168.0.132:7002/users/getName?userId=" + userId;
	        try {
	            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	            return response.getStatusCode().is2xxSuccessful() ? response.getBody() : "Unknown";
	        } catch (Exception e) {
	            return "Unknown";
	        }
	    }
	    
	    public void deleteTaskDocuments(String reportid) {
	        List<ProjectAnalyzer> docs = analyzerRepo.findByReportid(reportid);
	        if (!docs.isEmpty()) {
		        for (ProjectAnalyzer doc : docs) {
		        	analyzerRepo.deleteById(doc.getReportid()); 
		        }
	        }
	    }
	    
	    public List<AnalyzerTasks> getReportTasks(String reportid)
	    {
	    	return tasksRepo.findByReportid(reportid);
	    }
	    
	    public AnalyzedDataDto getAnalyzedData(String reportid) {
	        Optional<ProjectAnalyzer> projectAnalyzerOpt = analyzerRepo.getByReportid(reportid);
	        Optional<AnalyzedData> analyzedDataOpt = dataRepo.findByReportid(reportid);
	        List<AnalyzerTasks> analyzerTasks = tasksRepo.findByReportid(reportid);
	        List<FetchedDues> fetchedDues = duedateRepo.findByReportid(reportid);
            String fileUrl = "http://192.168.0.132:7007/analyzer/" + reportid;  

	        if (projectAnalyzerOpt.isPresent() && analyzedDataOpt.isPresent()) {
	            ProjectAnalyzer projectAnalyzer = projectAnalyzerOpt.get();
	            AnalyzedData analyzedData = analyzedDataOpt.get();

	            DataDto dataDto = new DataDto(
	                analyzedData.getDataid(),
	                analyzedData.getProjecttitle(),
	                analyzedData.getScope(),
	                analyzedData.getTechsummary(),
	                analyzedData.getTechrequirements(),
	                analyzedData.getDuedate(),
	                analyzedData.getClientname(),
	                analyzedData.getAddress(),
	                analyzedData.getEmail(),
	                analyzedData.getPhone()
	            );

	            List<TasksDto> tasksDtoList = analyzerTasks.stream()
	                .map(task -> new TasksDto(
	                    task.getTaskid(),
	                    task.getTitle(),
	                    task.getDuedate(),
	                    task.isIsadded()
	                ))
	                .collect(Collectors.toList());

	            List<DueDto> dueList = fetchedDues.stream()
	                .map(due -> new DueDto(
	                    due.getDateid(),
	                    due.getTitle(),
	                    due.getDuedate()
	                ))
	                .collect(Collectors.toList());

	            return new AnalyzedDataDto(
	                projectAnalyzer.getReportid(),
	                projectAnalyzer.getDocname(),
	                projectAnalyzer.getCreatedat(),
	                dataDto,
	                tasksDtoList,
	                dueList,
	                fileUrl
	                
	            );
	        }

	        return null; 
	    }

	    @Transactional
	    public boolean createAnalyzedData(AnalyzedDataDto reportBody) {
	        try {
	            AnalyzedData analyzedData = new AnalyzedData();
	            analyzedData.setProjecttitle(reportBody.getData().getProjecttitle());
	            analyzedData.setScope(reportBody.getData().getScope());
	            analyzedData.setTechsummary(reportBody.getData().getTechsummary());
	            analyzedData.setTechrequirements(reportBody.getData().getTechrequirements());
	            analyzedData.setClientname(reportBody.getData().getClientname());
	            analyzedData.setAddress(reportBody.getData().getAddress());
	            analyzedData.setEmail(reportBody.getData().getEmail());
	            analyzedData.setPhone(reportBody.getData().getPhone());
	            analyzedData.setReportid(reportBody.getReportid());

	            dataRepo.save(analyzedData);

	            List<AnalyzerTasks> tasks = reportBody.getTasksdto().stream().map(taskDto -> {
	                AnalyzerTasks task = new AnalyzerTasks();
	                task.setTitle(taskDto.getTitle());
	                task.setDuedate(taskDto.getDuedate());
	                task.setIsadded(false);
	                task.setReportid(reportBody.getReportid());
	                return task;
	            }).collect(Collectors.toList());

	            tasksRepo.saveAll(tasks);

	            List<FetchedDues> dues = reportBody.getDuedto().stream().map(dueDto -> {
	                FetchedDues due = new FetchedDues();
	                due.setDateid(dueDto.getDateid());
	                due.setTitle(dueDto.getTitle());
	                due.setDuedate(dueDto.getDuedate());
	                due.setReportid(reportBody.getReportid());
	                return due;
	            }).collect(Collectors.toList());

	            duedateRepo.saveAll(dues); 

	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    
	    public boolean updateCreated(String taskid) {
	        Optional<AnalyzerTasks> taskOptional = tasksRepo.getByTaskid(taskid);
	        
	        if (taskOptional.isPresent()) {
	            AnalyzerTasks task = taskOptional.get();
	            task.setIsadded(true);
	            tasksRepo.save(task);
	            return true;
	        }
	        
	        return false;
	    }

}
