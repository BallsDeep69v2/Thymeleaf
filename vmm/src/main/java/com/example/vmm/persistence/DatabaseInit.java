package com.example.vmm.persistence;

import com.example.vmm.domain.Grade;
import com.example.vmm.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.stream.Stream;

@Component
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(ApplicationArguments args) {
        try (Stream<String> lines = Files.lines(ResourceUtils.getFile("classpath:grades.csv").toPath())) {
            gradeRepository.saveAll(lines
                    .skip(1)
                    .map(s -> s.split(","))
                    .map(s -> new Grade(studentRepository.findById(s[0]).orElseThrow(), LocalDate.parse(s[1]), Subject.valueOf(s[2]), Integer.parseInt(s[3])))
                    .toList()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
