package com.sunil.booking.controller;

import com.sunil.booking.dto.MetaData;
import com.sunil.booking.dto.ResponseDTO;
import com.sunil.booking.enums.Status;
import com.sunil.booking.exception.BookingException;
import com.sunil.booking.exception.InvalidPageNumberException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
public class BaseController<V> {

    private static final String DATE_PATTERN = "dd-MM-yyyy";

    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    /**
     * This method will return specified objects of list by start and end parameter
     */
    public <T> List<T> paggingList(List<T> paggingList, Integer startIndex, Integer endIndex) throws BookingException {
        List<T> newPaggingList = new ArrayList<T>();
        try {
            if (startIndex != null && endIndex != null) {
                if ((endIndex > startIndex) && (startIndex >= 0) && (endIndex >= 0)) {
                    startIndex = startIndex == 0 ? 1 : startIndex;
                    endIndex = endIndex == 0 ? 1 : endIndex;
                    if (!paggingList.isEmpty() && paggingList.size() >= endIndex) {
                        newPaggingList.addAll(paggingList.subList(startIndex - 1, endIndex));
                        return newPaggingList;
                    } else if (!paggingList.isEmpty() && paggingList.size() >= startIndex
                            && paggingList.size() < endIndex) {
                        newPaggingList.addAll(paggingList.subList(startIndex - 1, paggingList.size()));
                        return newPaggingList;
                    }
                } else {
                    throw new BookingException("Please Enter Valid Start and End Limit");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BookingException("Error in data prosesssing");
        }
        return paggingList;
    }


    /**
     * This method builds response, whether data is available or not
     *
     * @param data
     * @param msg
     * @param start
     * @param end
     * @return
     * @throws InvalidPageNumberException
     */
    public ResponseEntity<ResponseDTO<List<V>>> buildResponse(List<V> data, String msgAvailable, String msgNotAvailable, Integer start, Integer end) throws InvalidPageNumberException {

        ResponseDTO<List<V>> responseDTO;
        Integer totalRecord = data.size();
        if (totalRecord == 0) {
            responseDTO = new ResponseDTO<List<V>>(msgNotAvailable, Status.FAIL, data);
        } else {
            data = paggingList(data, start, end);
            responseDTO = new ResponseDTO<List<V>>(msgAvailable, data, totalRecord,data.size());
        }
        return new ResponseEntity<ResponseDTO<List<V>>>(responseDTO, HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<V>> buildResponse(V data, String msgAvailable, String msgNotAvailable){
        ResponseDTO<V> responseDTO;
        if (null == data) {
            responseDTO = new ResponseDTO<>(msgNotAvailable, Status.FAIL, data);
        } else {
            responseDTO = new ResponseDTO<>(msgAvailable, data);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
