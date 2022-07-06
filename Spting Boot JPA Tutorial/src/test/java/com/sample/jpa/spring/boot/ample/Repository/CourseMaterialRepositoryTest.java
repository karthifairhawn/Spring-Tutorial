package com.sample.jpa.spring.boot.ample.Repository;

import java.util.List;

import com.sample.jpa.spring.boot.ample.Entity.Course;
import com.sample.jpa.spring.boot.ample.Entity.CourseMaterial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepositroy repo;

    @Test
    public void saveCourseMaterial(){
        Course course = Course.builder().title("DSA").credit(6).build();

        CourseMaterial courseMaterial = CourseMaterial.builder().
        URL("google.com").
        course(course).
        build();

        repo.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> allMaterials = repo.findAll();
        System.out.println(allMaterials);
    }
}
