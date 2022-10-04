package peaksoft.new_project_all;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import peaksoft.new_project_all.enums.Role;
import peaksoft.new_project_all.model.AuthInfo;
import peaksoft.new_project_all.model.Student;
import peaksoft.new_project_all.repository.StudentRepository;

@SpringBootApplication
public class NewProjectAllApplication {
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public NewProjectAllApplication(PasswordEncoder passwordEncoder, StudentRepository studentRepository) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NewProjectAllApplication.class, args);


    }

    @Bean
    public CommandLineRunner commandLiner() {
        return (args) -> {
            if (studentRepository.findByAuthInfoEmail("aijamal@gmail.com") == null) {
                AuthInfo authInfo = new AuthInfo("aijamal@gmail.com", passwordEncoder.encode("aijamal"), Role.STUDENT);
                Student student = new Student("Aijamal", 27, "87657", "aijamal@gmail.com", authInfo);
                studentRepository.save(student);
            }
        };
    }

}
