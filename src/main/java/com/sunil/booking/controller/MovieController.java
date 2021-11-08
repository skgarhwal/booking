package com.sunil.booking.controller;

import com.sunil.booking.dto.RequestDTO;
import com.sunil.booking.dto.ResponseDTO;
import com.sunil.booking.dto.TheatreDTO;
import com.sunil.booking.dto.TicketsDTO;
import com.sunil.booking.exception.InvalidPageNumberException;
import com.sunil.booking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController extends BaseController {

    @Autowired
    MovieService movieService;

    /**
     * Get
     * @param cityId
     * @param date
     * @param time
     * @param movieId
     * @return
     */
    @GetMapping("/theatre/city/{cityId}/date/{date}/time/{time}/movie/{movieId}")
    public ResponseEntity<ResponseDTO<List<TheatreDTO>>> getTheatresByCityAndMovie(@PathVariable("cityId") String cityId, @PathVariable("date") String date,
                                                                                  @PathVariable("time") String time, @PathVariable("movieId") String movieId) {
       List<TheatreDTO>  theatreList = movieService.getTheatresByCityAndMovie(cityId,date,time,movieId);

      return buildResponse(theatreList,"Avialable theatres","No Theatres available");
    }

    /**
     * This will check and return payment amount for the selected tickets.
     * @param requestDto
     * @return
     */
    @PostMapping(path = "/bookTickets",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> bookMovieTickets(@RequestBody RequestDTO requestDto) {
        ResponseDTO responseDTO = movieService.bookMovieTickets(requestDto);

        return buildResponse(responseDTO,"Tickets available","No tickets available");
    }

    /**
     * This will update the payment done from payment gateway
     * @param requestDto
     * @return
     */
    @PostMapping(path = "/updateTicketPayment",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateTicketPayment(@RequestBody RequestDTO requestDto) {
        ResponseDTO responseDTO = movieService.updateTicketPayment(requestDto);

        return buildResponse(responseDTO,"Payment Successdul","Payment Failed");
    }
}
