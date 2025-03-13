package com.project.analyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.analyzer.entity.AnalyzedData;

@Repository
public interface DataRepository extends JpaRepository<AnalyzedData, String> {


	Optional<AnalyzedData> findByReportid(String reportid);

}
