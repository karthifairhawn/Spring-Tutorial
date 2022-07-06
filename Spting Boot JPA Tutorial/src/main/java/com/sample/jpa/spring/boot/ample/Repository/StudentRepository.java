package com.sample.jpa.spring.boot.ample.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sample.jpa.spring.boot.ample.Entity.Student;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public List<Student> findByGuardianName(String guardianName);

    @Query("select s from Student s where s.lastName = ?1")
    Student getStudentByAboveQuery(String lastName);

    @Query("select s.lastName from Student s where s.firstName = ?1")
    String getStudentByAboveQuery2(String firstName);

    @Query(
            value = "SELECT * FROM public.student_tbl where student_tbl.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailNativeQuery(String emailId);

    @Query(
            value = "SELECT * FROM public.student_tbl where student_tbl.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailNativeNamedParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
            value="UPDATE public.student_tbl SET first_name=?1 where student_tbl.email_address=?2",
            nativeQuery = true
    )
    int updateStudentFirstNameByEmail(String firstName,String emailId);


}
