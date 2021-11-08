package com.sunil.booking.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class MetaData implements Serializable {

    private static final long serialVersionUID = 12121L;

    private Integer totalCount;
    private Integer pageCount;
    private Integer currentPage;
    private Integer pageSize;


}
