package peaksoft.new_project_all.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.new_project_all.model.AuthInfo;

import java.util.Optional;


public interface AuthInfoRepository extends JpaRepository<AuthInfo,Long> {

    Optional<AuthInfo> findByEmail(String email);
}
