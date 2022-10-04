package peaksoft.new_project_all.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.new_project_all.dto.response.TestResponse;
import peaksoft.new_project_all.model.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

    @Query("select new peaksoft.new_project_all.dto.response.TestInnerPageResponse(" +
            "t.id," +
            "t.name," +
            "t.duration) from Test t where t.id = ?1")
    TestResponse getTestById(Long id);
}