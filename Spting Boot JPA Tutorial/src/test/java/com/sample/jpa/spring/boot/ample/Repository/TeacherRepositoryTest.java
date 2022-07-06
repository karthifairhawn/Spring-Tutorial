package com.sample.jpa.spring.boot.ample.Repository;

import java.util.List;

import com.sample.jpa.spring.boot.ample.Entity.Course;
import com.sample.jpa.spring.boot.ample.Entity.Teacher;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherRepositoryTest {
    
    @Autowired
    TeacherRepository repo;

    @Test
    public void saveTeacher(){
        Course course1 = Course.builder().title("DAA").credit(1).build();
        Course course2 = Course.builder().title("TOC").credit(5).build();
        Teacher teacher = Teacher.builder().
                            teacherName("Pokemon").
                            courses(List.of(course1,course2)).
                            build();
        repo.save(teacher);
    }


}
