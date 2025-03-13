package com.project.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.analyzer.entity.FetchedDues;

@Repository
public interface DuedateRepository extends JpaRepository<FetchedDues, String> {

	List<FetchedDues> findByReportid(String reportid);

}
