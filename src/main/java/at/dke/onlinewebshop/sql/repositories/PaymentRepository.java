package at.dke.onlinewebshop.sql.repositories;

import at.dke.onlinewebshop.sql.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
