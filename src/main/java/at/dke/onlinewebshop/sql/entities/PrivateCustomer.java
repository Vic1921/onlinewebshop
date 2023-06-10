package at.dke.onlinewebshop.sql.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "private_customers")
public class PrivateCustomer extends Customer {


}
