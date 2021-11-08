package com.sunil.booking.service;

import com.sunil.booking.dto.RequestDTO;
import com.sunil.booking.dto.ResponseDTO;
import com.sunil.booking.dto.TheatreDTO;
import com.sunil.booking.model.Movie;
import com.sunil.booking.model.MovieShow;
import com.sunil.booking.model.TheatreSeat;
import com.sunil.booking.repository.read.MovieReadRepository;
import com.sunil.booking.repository.write.TheatreSeatWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieReadRepository movieReadRepository;

    @Autowired
    TheatreSeatWriteRepository theatreSeatWriteRepository;

    public List<TheatreDTO> getTheatresByCityAndMovie(String cityId, String date, String time, String movieId) {
        Optional<Movie> movie = movieReadRepository.findById(Long.parseLong(movieId));

        if (movie.isPresent()) {
            try {
                return getTheatresByCityAndDate(movie, cityId, date, time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return null;

    }

    private List<TheatreDTO> getTheatresByCityAndDate(Optional<Movie> movie, String str_city, String str_date, String time) throws ParseException {

        List<TheatreDTO> theatreList = new ArrayList<>();
        Movie movieObj = movie.get();
        List<MovieShow> shows = movieObj.getShows();
        int cityId = Integer.parseInt(str_city);
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy HH:MM");
        Date date = formatter.parse(str_date + " " + time);

        List<MovieShow> filteredShows = shows.stream().filter(movieShow -> movieShow.getTheatre().getCityId() == cityId
                && movieShow.getStartTime().before(date)
                && movieShow.getEndTime().after(date)).collect(Collectors.toList());

        for (MovieShow movieShow : shows) {
            if (movieShow.getTheatre().getCityId() == cityId && movieShow.getStartTime().before(date)
                    && movieShow.getEndTime().after(date)) {
                TheatreDTO theatreDTO = new TheatreDTO();
                theatreDTO.setName(movieShow.getTheatre().getName());
                theatreDTO.setAddress(movieShow.getTheatre().getAddress().getStreetAddress());
                theatreList.add(theatreDTO);
            }

        }
        return theatreList;
    }

    public ResponseDTO bookMovieTickets(RequestDTO requestDto) {
        //1. Reserve the requested tickets for 5 minutes, they won't be available to other users for booking.
        //2. Based on the prices of the selected tickets, return amount to be paid.

        double totalPrice = 0;
        List<TheatreSeat> theatreSeats = requestDto.getTheatreSeats();
        for (TheatreSeat theatreSeat: theatreSeats) {
            theatreSeat.setReserved(Boolean.TRUE);
            totalPrice += theatreSeat.getPrice();
            theatreSeatWriteRepository.save(theatreSeat);

        }

        return new ResponseDTO("Success", totalPrice);
    }

    public ResponseDTO updateTicketPayment(RequestDTO requestDto) {
        //1. On successful payment, marks those tickets as booked.  Send communication to user via SMS, email, whatsapp etc.

        return new ResponseDTO("Success");
    }
}
