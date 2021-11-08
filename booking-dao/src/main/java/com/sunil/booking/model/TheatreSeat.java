package com.sunil.booking.model;


import com.sunil.booking.enums.SeatType;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
@Table(name = "theatre_seat")
public class TheatreSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isReserved;
    private boolean isBooked;
    private String seatRow;
    private String seatColumn;
    private double price;
    private SeatType seatType;

    @ManyToOne()
    @JoinColumn(name="show_id")
    private MovieShow movieShow;

}
