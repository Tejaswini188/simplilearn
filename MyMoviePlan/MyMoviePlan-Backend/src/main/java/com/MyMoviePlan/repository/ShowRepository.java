package com.MyMoviePlan.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MyMoviePlan.model.Show;



@Repository
public interface ShowRepository extends CrudRepository<Show, Integer> {
	List<Show> findByShowname(@Param("showName") String showName);
	

	@Query("SELECT t FROM Show t WHERE t.movie.moviename LIKE %?1%")
	List<Show> findByMovieAndSort(String name, Sort sort);
	/*
	 * @Query(HQL - JOIN QUERY) 
	 * List<Movie> findByDirectorName();
	 */

}
