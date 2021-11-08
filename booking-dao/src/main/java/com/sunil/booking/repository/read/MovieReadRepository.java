package com.sunil.booking.repository.read;

import com.sunil.booking.annotation.ReadOnlyRepository;
import com.sunil.booking.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@ReadOnlyRepository
public interface MovieReadRepository extends CrudRepository<Movie, Long> {
}
