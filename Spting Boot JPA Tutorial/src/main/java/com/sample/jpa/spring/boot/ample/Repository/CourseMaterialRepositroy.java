package com.sample.jpa.spring.boot.ample.Repository;

import com.sample.jpa.spring.boot.ample.Entity.CourseMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepositroy extends JpaRepository<CourseMaterial,Long>{

    
}
