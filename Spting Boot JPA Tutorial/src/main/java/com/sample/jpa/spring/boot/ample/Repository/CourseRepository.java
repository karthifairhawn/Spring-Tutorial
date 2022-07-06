package com.sample.jpa.spring.boot.ample.Repository;

import com.sample.jpa.spring.boot.ample.Entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{
    
}