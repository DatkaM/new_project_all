package peaksoft.new_project_all.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.new_project_all.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}