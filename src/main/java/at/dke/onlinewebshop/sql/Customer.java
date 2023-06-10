package at.dke.onlinewebshop.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

    @Id
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


}
