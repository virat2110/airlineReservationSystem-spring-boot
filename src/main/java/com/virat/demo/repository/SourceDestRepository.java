package com.virat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virat.demo.model.SourceDest;

@Repository
public interface SourceDestRepository extends JpaRepository<SourceDest, Integer>{

}
