package com.MyMoviePlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MyMoviePlan.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData,Integer>{
	
	 UserData findByUser_Id(long id);

}
