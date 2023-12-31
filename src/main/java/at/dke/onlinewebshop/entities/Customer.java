package at.dke.onlinewebshop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
//@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false)
    private String  password;

    @OneToOne
    @JoinColumn(name = "vendor_name")
    private Vendor vendor;

    @OneToMany(mappedBy = "customer")
    private List<Article> articles;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String email, String lastname, String surname, String address, String password) {
        this.email = email;
        this.lastname = lastname;
        this.surname = surname;
        this.address = address;
        this.password = password;
    }
}
