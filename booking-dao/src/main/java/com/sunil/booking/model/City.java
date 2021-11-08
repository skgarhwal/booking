package com.sunil.booking.model;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Audited
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    private String name;
    private String zipCode;

    @OneToMany(fetch= FetchType.LAZY , mappedBy = "city")
    private List<Address> addressList;

}
