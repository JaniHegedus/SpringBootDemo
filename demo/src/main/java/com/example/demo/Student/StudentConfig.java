package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student Jani =
                     new Student(
                             1L,
                             "János",
                             "hegedusjanos2002@gmail.com",
                             LocalDate.of(2002, Month.NOVEMBER,13));
            Student Korb =
                    new Student(
                            2L,
                            "András",
                            "korbandras@gmail.com",
                            LocalDate.of(2002, Month.JUNE,4));
            studentRepository.saveAll(
                    List.of(Jani,Korb)
            );
        };

    }
}
