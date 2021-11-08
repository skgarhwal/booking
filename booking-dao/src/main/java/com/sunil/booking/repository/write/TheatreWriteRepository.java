package com.sunil.booking.repository.write;

import com.sunil.booking.model.Theatre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreWriteRepository extends CrudRepository<Theatre, Long> {
}
