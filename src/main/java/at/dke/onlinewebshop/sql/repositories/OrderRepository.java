package at.dke.onlinewebshop.sql.repositories;


import at.dke.onlinewebshop.sql.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}