package com.sunil.booking.dto;

import com.sunil.booking.model.TheatreSeat;
import lombok.Data;

import java.util.List;

@Data
public class RequestDTO {
    private String theatreId;
    private String date;
    private String time;
    private List<TheatreSeat> theatreSeats;

}
