package com.sunil.booking.model;

import com.sunil.booking.enums.AccountStatus;
import com.sunil.booking.enums.UserType;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Audited
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;


    private UserType userType;
    private AccountStatus status;

    @OneToMany(fetch=FetchType.LAZY , mappedBy = "user")
    private List<Address> addressList;

}
