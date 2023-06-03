package com.MyMoviePlan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MyMoviePlan.model.Seats;

public interface SeatRepository extends JpaRepository<Seats, Integer>{
	
	@Query("SELECT t FROM Seats t WHERE t.showId=?1")
	List<Seats> findSeatByShowId(int id); 

}
