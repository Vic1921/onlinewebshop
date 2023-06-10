package at.dke.onlinewebshop.sql.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @Column(name = "vendor_name", nullable = false)
    private String name;

    @Column(name = "vendor_address", nullable = false)
    private String address;

    @Column(name = "vendor_svnr", nullable = false)
    private String svnr;

    @OneToOne(mappedBy = "vendor")
    private Customer customer;
}
