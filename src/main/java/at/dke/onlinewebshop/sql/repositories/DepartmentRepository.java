package at.dke.onlinewebshop.sql.repositories;

import at.dke.onlinewebshop.sql.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
