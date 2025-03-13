package com.project.analyzer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.analyzer.entity.ProjectAnalyzer;

@Repository
public interface AnalyzerRepository extends JpaRepository<ProjectAnalyzer, String> {

	List<ProjectAnalyzer> findByReportid(String reportid);

	Optional<ProjectAnalyzer> getByReportid(String reportid);

}
