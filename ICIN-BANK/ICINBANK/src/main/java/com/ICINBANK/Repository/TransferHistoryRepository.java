package com.ICINBANK.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ICINBANK.Entity.Transfer;

@Repository
public interface TransferHistoryRepository extends JpaRepository<Transfer, Integer>{

	public List<Transfer> findBySaccount(long saccount);
	public List<Transfer> findByRaccount(long raccount);
	
}
