package com.project.analyzer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.analyzer.dto.AnalyzedDataDto;
import com.project.analyzer.dto.DocumentResponse;
import com.project.analyzer.entity.ProjectAnalyzer;
import com.project.analyzer.service.AnalyzerService;

@RestController
@RequestMapping("/analyzer")
public class AnalyzerController {
	
	@Autowired
	private AnalyzerService analyzerService;
	
	@PostMapping("/upload")
	public ResponseEntity<Map<String, Object>> uploadFiles(@RequestParam("files") MultipartFile[] files,
	                                                       @RequestParam String userid) {
	    List<String> documentIds = new ArrayList<>();
	    
	    for (MultipartFile file : files) {
	    	ProjectAnalyzer document = analyzerService.uploadDocuments(file, userid);
	        documentIds.add(document.getReportid());
	    }
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("docIds", documentIds);
	    
	    return ResponseEntity.ok(response);
	}

	 @GetMapping("/getAllDocuments")
	    public ResponseEntity<?> getAllDocuments() {
	        List<DocumentResponse> documents = analyzerService.getAllDocument();
	        if (documents!= null) {
	        	
	            return  ResponseEntity.ok(documents);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @GetMapping("/{reportid}")
	 public ResponseEntity<ByteArrayResource> getDocuments(
	         @PathVariable String reportid,
	         @RequestParam(required = false, defaultValue = "false") boolean download) throws IOException {
	     Optional<ProjectAnalyzer> documents = analyzerService.getDocument(reportid);
	     
	     if (documents.isPresent()) {
	         ProjectAnalyzer img = documents.get();
	         ByteArrayResource resource = new ByteArrayResource(img.getFile());

	         HttpHeaders headers = new HttpHeaders();
	         headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(img.getDocname()))); 
	         headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(img.getFile().length));

	         if (download) {
	             headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + img.getDocname() + "\"");
	         } else {
	             headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + img.getDocname() + "\"");
	         }

	         return ResponseEntity.ok()
	                 .headers(headers)
	                 .body(resource);
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }

	    @GetMapping("/report")
	    public ResponseEntity<?> getAnalyzedData(@RequestParam String reportid) {
	    	AnalyzedDataDto analyzeddata = analyzerService.getAnalyzedData(reportid);
	    	
	    	if(analyzeddata!= null)
	    	{    	 	
	            return  ResponseEntity.ok(analyzeddata);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
    
	    @PostMapping("/createdata")
	    public ResponseEntity<?> createAnalyzedData(@RequestBody AnalyzedDataDto reportbody) {
	    	boolean response = analyzerService.createAnalyzedData(reportbody);
	    	
	    	if(response)
	    	{    	 	
	            return  ResponseEntity.ok("Data created successfully");
	        } else {
	            return ResponseEntity.badRequest().body("Failed to create data");
	        }
	    }
	@PutMapping("/markAdded")
    public ResponseEntity<?> markAdded(@RequestParam String taskid) {
    	boolean response = analyzerService.updateCreated(taskid);
    	
    	if(response)
    	{    	 	
            return  ResponseEntity.ok("task marked as added successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to mark as added");
        }
    }
}
