package at.dke.onlinewebshop.sql.repositories;

import at.dke.onlinewebshop.sql.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
