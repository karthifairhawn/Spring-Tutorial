package com.sample.jpa.spring.boot.ample.Repository;

import java.util.List;

import com.sample.jpa.spring.boot.ample.Entity.Course;
import com.sample.jpa.spring.boot.ample.Entity.Teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourse(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
        
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher
                            .builder()
                            .teacherName("Doremon")
                            .build();
        Course course = Course.builder()
                        .title("Python")
                        .credit(5)
                        .teacher(teacher)
                        .build();
        courseRepository.save(course);
    }
}
