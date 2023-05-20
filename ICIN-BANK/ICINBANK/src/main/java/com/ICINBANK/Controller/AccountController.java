package com.ICINBANK.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ICINBANK.Repository.AccountRepository;
import com.ICINBANK.Repository.SaccountRepository;
import com.ICINBANK.Details.TransactionDetails;
import com.ICINBANK.Details.TransferDetails;
import com.ICINBANK.Entity.Account;
import com.ICINBANK.Entity.Saccount;
import com.ICINBANK.Entity.Transfer;
import com.ICINBANK.Entity.UserHistory;
import com.ICINBANK.Response.DepositResponse;
import com.ICINBANK.Response.TransferResponse;
import com.ICINBANK.Response.WithdrawResponse;
import com.ICINBANK.Service.AccountService;
import com.ICINBANK.Service.SaccountService;
import com.ICINBANK.Service.TransferHistoryService;
import com.ICINBANK.Service.UserHistoryService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Autowired 
	private SaccountService sservice;
	
	@Autowired
	private UserHistoryService hservice;
	
	@Autowired
	private TransferHistoryService tservice;
	
	@Autowired
	private AccountRepository aRepository;
	
	@Autowired
	private SaccountRepository sRepository;
	
	private final String ifsc="ICIN7465";
	
	public static boolean isprimary(long account) {
		String s = Long.toString(account).substring(0, 10);
		String check="3914918201";
		if(s.equals(check)) {
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	
	@GetMapping("/account/Details/{account}")
	public Account getAccountDetails(@PathVariable("account") int account ) {
		
		return service.getAccountDetails(account);
		
	}
	
	@PutMapping("/account/profile")
	public Account updateProfile(@RequestBody Account account) {
		return service.updateAccount(account);
	}
	
	@GetMapping("/account/getprimary/{username}")
	public Account getPrimaryDetails(@PathVariable("username") String username) {
		return service.getAccount(username);
	}
	
	@GetMapping("/account/getsaving/{username}")
	public Saccount getSavingDetails(@PathVariable("username") String username) {
		return sservice.getAccount(username);
	}
	
	@PostMapping("/account/deposit")
	public DepositResponse deposit(@RequestBody TransactionDetails Details) {
		//aRepository.findByUsername(aRepository.findByAccno(Details.getAccount()).getUsername());
		if(isprimary(Details.getAccount())) {
			return service.deposit(Details.getAccount(), Details.getAmount());
		}
		else {
			return sservice.deposit(Details.getAccount(), Details.getAmount());
		}
	}
	
	@PostMapping("/account/withdraw")
	public WithdrawResponse withdraw(@RequestBody TransactionDetails Details) {
		
		if(isprimary(Details.getAccount())) {
		return service.withdraw(Details.getAccount(), Details.getAmount());
		}
		else {
			return sservice.withdraw(Details.getAccount(), Details.getAmount());
		}
	}
	
	@PostMapping("/account/transfer")
	public TransferResponse transfer(@RequestBody TransferDetails Details) {
		try {
			if(Details.getIfsc().equals(ifsc)) 
			{
						Account p=aRepository.findByUsername(Details.getUsername());
						Saccount s=sRepository.findByUsername(Details.getUsername());
						
						if(p.getAccno()==Details.getSaccount() || s.getAccno()==Details.getSaccount()) {
						//String len = Integer.toString(Details.getSaccount());
						if(isprimary(Details.getSaccount())) {
						return service.transfer(Details.getSaccount(), Details.getRaccount(), Details.getAmount());
						}
						else
						{
							return sservice.transfer(Details.getSaccount(), Details.getRaccount(), Details.getAmount());
						}
						}
						else {
							TransferResponse response=new TransferResponse();
							response.setSaccount(Details.getSaccount());
							response.setResponseMessage("Dear user You can only transfer funds from the accounts registed with you");
							response.setTransferStatus(false);
							return response;
			}
			}
			else {
				TransferResponse response=new TransferResponse();
						response.setSaccount(Details.getSaccount());
						response.setResponseMessage("IFSC code is incorrect");
						response.setTransferStatus(false);
						return response;
			}
		} catch (Exception e) {
			TransferResponse response=new TransferResponse();
			response.setSaccount(Details.getSaccount());
			response.setResponseMessage("Please provide an IFSC code");
			response.setTransferStatus(false);
			return response;
			
		}
	}
	
	@GetMapping("/account/getHistory/{account}")
	public List<UserHistory> getHistory(@PathVariable("account") long account )
	{
		List<UserHistory> history=hservice.getHistory(account);
		Collections.reverse(history);
		return history;
	}
	
	@GetMapping("/account/getTransfers/{account}")
	public List<Transfer> getTransfers(@PathVariable("account") long account )
	{
		return tservice.getTransfers(account);
	}


}
