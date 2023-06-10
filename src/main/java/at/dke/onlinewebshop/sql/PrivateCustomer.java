package at.dke.onlinewebshop.sql;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "corporate_customers")
public class PrivateCustomer extends Customer {


}
