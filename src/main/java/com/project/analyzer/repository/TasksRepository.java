package com.project.analyzer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.analyzer.entity.AnalyzerTasks;

@Repository
public interface TasksRepository extends JpaRepository<AnalyzerTasks, String> {

	List<AnalyzerTasks> findByReportid(String reportid);

	Optional<AnalyzerTasks> getByTaskid(String taskid);

}
