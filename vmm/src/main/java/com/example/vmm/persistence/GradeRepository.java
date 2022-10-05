package com.example.vmm.persistence;


import com.example.vmm.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("""
            select grade
            from Grade grade
            where grade.student.id = :id
            """)
    List<Grade> getAllGradesByStudentId(String id);


}
