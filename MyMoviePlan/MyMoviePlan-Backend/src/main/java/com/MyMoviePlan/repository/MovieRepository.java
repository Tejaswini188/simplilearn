package com.MyMoviePlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;


import com.MyMoviePlan.model.Movie;


@EnableTransactionManagement
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	//List<Movie> findByMovienameStartingWith(@Param("movieName") String movieName);

	
	Movie findByMovienameIgnoreCase(String name);
	
	@Query("SELECT t FROM Movie t WHERE t.moviestatus=1 and t.moviename LIKE %?1%")
	List<Movie> findByMovienameStartingWithEnabled(String name, Sort sort);
	
	
	@Query("SELECT t FROM Movie t WHERE t.moviestatus=1")
	List<Movie> findAllMovieEnabled(Sort sort);
	
	@Query("SELECT t FROM Movie t WHERE t.moviestatus=1 and t.language LIKE %?1%")
	List<Movie> findAllMovieByLanguageEnabled(String name, Sort sortt);

	/*
	 * @Query(HQL - JOIN QUERY) 
	 * List<Movie> findByDirectorName();
	 */
	
	@Query("SELECT t FROM Movie t WHERE t.moviestatus=1 and t.genre.name LIKE %?1%")
	List<Movie> findByGenreAndSort(String name, Sort sort);
	/*
	@Modifying
	@Query("update EARAttachment ear set ear.status = ?1 where ear.id = ?2")
	int setStatusForEARAttachment(Integer status, Long id);
	
	@Modifying
	@Query("update EARAttachment ear set ear.status = :status where ear.id = :id")
	int setStatusForEARAttachment(@Param("status") Integer status, @Param("id") Long id);
	*/
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("update Movie a set a.moviestatus = :status where a.id = :id")
	int setStatusForMovie(@Param("status") Integer status, @Param("id") Integer id);

}
