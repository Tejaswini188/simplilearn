package com.ICINBANK.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ICINBANK.Entity.ChequebookRequest;

public interface ChequeBookRepository extends JpaRepository<ChequebookRequest, Integer>{

	public List<ChequebookRequest> findByAccount(long account);
}
