package com.sunil.booking.model;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Audited
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    private String title;
    private String description;
    private int durationInMins;
    private String language;
    private Date releaseDate;
    private String genre;
    private int movieAddedByUser;

    @OneToMany(fetch=FetchType.LAZY , mappedBy = "movie")
    private List<MovieShow> shows;

}
