package peaksoft.new_project_all.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.new_project_all.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByAuthInfoEmail(String email);
}