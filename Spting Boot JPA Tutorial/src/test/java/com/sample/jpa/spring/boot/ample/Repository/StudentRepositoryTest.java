package com.sample.jpa.spring.boot.ample.Repository;

import com.sample.jpa.spring.boot.ample.Entity.Guardian;
import com.sample.jpa.spring.boot.ample.Entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() throws Exception {
        Student student = Student.builder().
                            firstName("Karthick").lastName("B").emailId("kfh@gmail.com").
                            //guardianEmail("hackerkarthi@gmail.com").
                            //guardianMobile("9999999999").
                            // guardianName("Guardian").
                            build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() throws Exception {
        Guardian guardian = new Guardian("Guardian","123@gmail.com","999999999");

        Student student = Student.builder().firstName("Karthi").lastName("Bro").emailId("321@gmail.com").guardian(guardian).build();

        studentRepository.save(student);

    }

    @Test
    public void findAll() throws Exception {
        List<Student> students = studentRepository.findAll();
        System.out.println("All Students :" + students);
    }

    @Test
    public void findbyGuardianName() throws Exception {
        List<Student> students = studentRepository.findByGuardianName("Guardian");
        System.out.println("All Students :" + students);
    }

    @Test
    public void getStudentByAboveQuery(){
        Student s = studentRepository.getStudentByAboveQuery("Bro");
        System.out.println(s);
    }

    @Test
    public void getStudentByAboveQuery2(){
        String s = studentRepository.getStudentByAboveQuery2("Karthi");
        System.out.println(s);
    }

    @Test
    public void getStudentByEmailNativeQuery(){
        Student s = studentRepository.getStudentByEmailNativeQuery("321@gmail.com");
        System.out.println(s);
    }

    @Test
    public void getStudentByEmailNativeNamedParam(){
        Student s = studentRepository.getStudentByEmailNativeNamedParam("321@gmail.com");
        System.out.println(s);
    }

    @Test
    public void updateStudentFirstNameByEmail(){
        int x = studentRepository.updateStudentFirstNameByEmail("karthi","321@gmail.com");
        System.out.println(x);
    }
}