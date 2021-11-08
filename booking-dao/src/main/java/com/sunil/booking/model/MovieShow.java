package com.sunil.booking.model;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Audited
@Table(name = "movie_show")
public class MovieShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private Long showId;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne()
    @JoinColumn(name="theatre_id")
    private Theatre theatre;

    @ManyToOne()
    @JoinColumn(name="movie_id")
    private Movie movie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieShow")
    private List<TheatreSeat> seats;

}
