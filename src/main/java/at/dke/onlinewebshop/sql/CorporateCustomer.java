package at.dke.onlinewebshop.sql;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "corporate_customers")
public class CorporateCustomer extends Customer {

    @Column(name = "tax_number", nullable = false)
    private String taxNumber;


}
