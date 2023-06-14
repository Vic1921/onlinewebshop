package at.dke.onlinewebshop.sql.repositories;

import at.dke.onlinewebshop.sql.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
