package com.project.analyzer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.project.analyzer.dto.AnalyzedDataDto;
import com.project.analyzer.dto.DocumentResponse;
import com.project.analyzer.entity.ProjectAnalyzer;
import com.project.analyzer.repository.AnalyzerRepository;
import com.project.analyzer.repository.DataRepository;
import com.project.analyzer.repository.TasksRepository;

public class AnalyzerServiceTest {

    @Mock
    private AnalyzerRepository analyzerRepo;

    @Mock
    private TasksRepository tasksRepo;

    @Mock
    private DataRepository dataRepo;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private AnalyzerService analyzerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadDocuments() throws Exception {
        ProjectAnalyzer document = new ProjectAnalyzer();
        document.setReportid("123");
        document.setDocname("Test Report");

        when(file.getOriginalFilename()).thenReturn("test.txt");
        when(file.getSize()).thenReturn(100L);
        when(file.getBytes()).thenReturn("Test Content".getBytes());
        when(analyzerRepo.save(any())).thenReturn(document);

        ProjectAnalyzer result = analyzerService.uploadDocuments(file, "user1");

        assertNotNull(result);
    }

    @Test
    void testGetAllDocument() {
        ProjectAnalyzer document = new ProjectAnalyzer();
        document.setReportid("123");
        document.setDocname("Test Report");
        document.setCreatedby("user1");
        document.setCreatedat(LocalDateTime.now());

        when(analyzerRepo.findAll()).thenReturn(Collections.singletonList(document));
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(ResponseEntity.ok("User Name"));

        List<DocumentResponse> result = analyzerService.getAllDocument();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test Report", result.get(0).getDocname());
    }

    @Test
    void testGetDocument() {
        ProjectAnalyzer document = new ProjectAnalyzer();
        document.setReportid("123");

        when(analyzerRepo.getByReportid("123")).thenReturn(Optional.of(document));

        Optional<ProjectAnalyzer> result = analyzerService.getDocument("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getReportid());
    }

    @Test
    void testGetAnalyzedData_NotFound() {
        when(analyzerRepo.getByReportid("123")).thenReturn(Optional.empty());
        when(dataRepo.findByReportid("123")).thenReturn(Optional.empty());

        AnalyzedDataDto result = analyzerService.getAnalyzedData("123");

        assertNull(result);
    }
}
