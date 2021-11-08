package com.sunil.booking.repository.read;

import com.sunil.booking.annotation.ReadOnlyRepository;
import com.sunil.booking.model.Theatre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@ReadOnlyRepository
@Repository
public interface TheatreReadRepository extends CrudRepository<Theatre, Long> {
}
