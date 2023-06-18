package at.dke.onlinewebshop.sql.repositories;

import at.dke.onlinewebshop.sql.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}