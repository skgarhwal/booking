package com.sunil.booking.search;

import java.util.Date;
import java.util.List;

import com.sunil.booking.model.Movie;

public interface Search {


	List<Movie> searchByTitle(String title);

	List<Movie> searchByLanguage(String language);

	List<Movie> searchByGenre(String genre);

	List<Movie> searchByReleaseDate(Date releaseDate);

	public List<Movie> searchByCity(String cityName);
}
