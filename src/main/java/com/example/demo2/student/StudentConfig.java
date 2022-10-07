package com.example.demo2.student;

import net.bytebuddy.asm.Advice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
         Student quado = new Student(
                    "Chikwado",
                    "chi@gmail.com",
                 LocalDate.of(2000, Month.JANUARY, 4)

            );
         Student ibra = new Student(
                 "Ibrahim",
                 "ibra@gmail.com",
                 LocalDate.of(2002,Month.MAY, 11)
         );
         repository.saveAll(List.of(quado, ibra));
        };
    }
}
