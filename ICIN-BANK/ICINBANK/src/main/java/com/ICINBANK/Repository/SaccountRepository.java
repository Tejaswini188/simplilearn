package com.ICINBANK.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ICINBANK.Entity.Saccount;

@Repository
public interface SaccountRepository extends JpaRepository<Saccount, Integer>{

	public Saccount findByUsername(String username);
	public Saccount findByAccno(long accno);
}
