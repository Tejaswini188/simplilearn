package com.MyMoviePlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MyMoviePlan.model.PaymentPOJO;



@Repository
public interface PaymentRepository extends JpaRepository<PaymentPOJO, Integer> {
	//List<Show> findByShowname(@Param("showName") String showName);
	
	@Query("SELECT t FROM PaymentPOJO t WHERE t.description LIKE %?1%")
	PaymentPOJO findByDescriptionLike(String name);

	/*
	 * @Query(HQL - JOIN QUERY) 
	 * List<Movie> findByDirectorName();
	 */

}
