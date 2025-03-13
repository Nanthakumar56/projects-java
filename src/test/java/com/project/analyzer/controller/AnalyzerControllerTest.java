package com.project.analyzer.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.analyzer.dto.AnalyzedDataDto;
import com.project.analyzer.dto.DocumentResponse;
import com.project.analyzer.entity.ProjectAnalyzer;
import com.project.analyzer.service.AnalyzerService;

public class AnalyzerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AnalyzerService analyzerService;

    @InjectMocks
    private AnalyzerController analyzerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(analyzerController).build();
    }

    @Test
    void testUploadFiles() throws Exception {
        MockMultipartFile file = new MockMultipartFile("files", "test.txt", "text/plain", "Test Content".getBytes());
        ProjectAnalyzer analyzer = new ProjectAnalyzer();
        analyzer.setReportid("123");

        when(analyzerService.uploadDocuments(any(), anyString())).thenReturn(analyzer);

        mockMvc.perform(multipart("/analyzer/upload").file(file).param("userid", "user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.docIds[0]").value("123"));
    }

    @Test
    void testGetAllDocuments() throws Exception {
        DocumentResponse docResponse = new DocumentResponse();
        when(analyzerService.getAllDocument()).thenReturn(Collections.singletonList(docResponse));

        mockMvc.perform(get("/analyzer/getAllDocuments"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDocuments() throws Exception {
        ProjectAnalyzer document = new ProjectAnalyzer();
        document.setDocname("test.txt");
        document.setFile("Test Content".getBytes());

        when(analyzerService.getDocument("123")).thenReturn(Optional.of(document));

        mockMvc.perform(get("/analyzer/123"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Disposition"));
    }

    @Test
    void testGetAnalyzedData() throws Exception {
        AnalyzedDataDto analyzedData = new AnalyzedDataDto();
        when(analyzerService.getAnalyzedData("123")).thenReturn(analyzedData);

        mockMvc.perform(get("/analyzer/report").param("reportid", "123"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateAnalyzedData() throws Exception {
        AnalyzedDataDto analyzedData = new AnalyzedDataDto();
        when(analyzerService.createAnalyzedData(any())).thenReturn(true);

        mockMvc.perform(post("/analyzer/createdata")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Data created successfully"));
    }
}
