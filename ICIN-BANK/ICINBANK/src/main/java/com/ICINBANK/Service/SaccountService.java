package com.ICINBANK.Service;

import com.ICINBANK.Entity.Account;
import com.ICINBANK.Response.TransferResponse;
import com.ICINBANK.Entity.User;
import com.ICINBANK.Response.WithdrawResponse;
import com.ICINBANK.Response.DepositResponse;
import com.ICINBANK.Entity.Saccount;
import com.ICINBANK.Repository.UserRepository;
import com.ICINBANK.Repository.AccountRepository;
import com.ICINBANK.Service.TransferHistoryService;
import com.ICINBANK.Service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ICINBANK.Repository.SaccountRepository;
import org.springframework.stereotype.Service;

@Service
public class SaccountService
{
    @Autowired
    private SaccountRepository dao;
    @Autowired
    private UserHistoryService service;
    @Autowired
    private TransferHistoryService tservice;
    @Autowired
    private AccountRepository adao;
    @Autowired
    private UserRepository udao;
    private final String bankCode = "3914";
    private final String countryCode = "91";
    private final String branchCode = "820";
    private final String accountcode = "2";
    
    public Saccount getAccount(final String username) {
        return this.dao.findByUsername(username);
    }
    
    public Saccount getAccountDetails(final long account) {
        return this.dao.findByAccno(account);
    }
    
    public long generate_saving(final int userId) {
        final String accNo = "3914918202" + String.valueOf(userId);
        return Long.parseLong(accNo);
    }
    
    public Saccount newAccount(final String username, final int userId) {
        final Saccount account = new Saccount();
        account.setUsername(username);
        account.setAccno(this.generate_saving(userId));
        account.setUser(this.udao.findByUsername(username));
        return (Saccount)this.dao.save((Object)account);
    }
    
    public DepositResponse deposit(final long acc, final int amount) {
        final DepositResponse response = new DepositResponse();
        boolean flag = true;
        try {
            final Saccount account = this.dao.findByAccno(acc);
            account.setBalance(account.getBalance() + amount);
            this.service.addAction(acc, amount, account.getBalance(), "deposit");
            this.dao.save((Object)account);
            response.setResponseMessage("Rs." + amount + " successfully deposited into your account balance is now Rs." + account.getBalance());
            response.setDepositStatus(flag);
        }
        catch (Exception e) {
            flag = false;
            response.setResponseMessage("Account number is incorrect");
            response.setDepositStatus(flag);
        }
        response.setAccount(acc);
        return response;
    }
    
    public WithdrawResponse withdraw(final long acc, final int amount) {
        final WithdrawResponse response = new WithdrawResponse();
        boolean flag = true;
        try {
            final Saccount account = this.dao.findByAccno(acc);
            final User user = this.udao.findByUsername(account.getUsername());
            if (user.getFeatureStatus() == 2 || user.getFeatureStatus() == 3) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    this.service.addAction(acc, amount, account.getBalance(), "withdraw");
                    this.dao.save((Object)account);
                    response.setResponseMessage("Rs." + amount + " successfully withdrawn your account balance is now Rs." + account.getBalance());
                    response.setWithdrawStatus(flag);
                }
                else {
                    flag = false;
                    response.setResponseMessage("Insufficient funds to complete the transaction");
                    response.setWithdrawStatus(flag);
                }
            }
            else {
                flag = false;
                response.setResponseMessage("This function is not available for your account");
                response.setWithdrawStatus(flag);
            }
        }
        catch (Exception e) {
            flag = false;
            response.setResponseMessage("Account number is incorrect");
            response.setWithdrawStatus(flag);
        }
        response.setAccount(acc);
        return response;
    }
    
    public TransferResponse transfer(final long saccount, final long raccount, final int amount) {
        final TransferResponse response = new TransferResponse();
        boolean flag = true;
        try {
            final Saccount senderAccount = this.dao.findByAccno(saccount);
            if (isprimary(raccount)) {
                final Account receiverAccount = this.adao.findByAccno(raccount);
                if (senderAccount.getAccno() != receiverAccount.getAccno()) {
                    if (senderAccount.getBalance() >= amount) {
                        final User user = this.udao.findByUsername(senderAccount.getUsername());
                        if (user.getFeatureStatus() == 3) {
                            senderAccount.setBalance(senderAccount.getBalance() - amount);
                            receiverAccount.setBalance(receiverAccount.getBalance() + amount);
                            this.tservice.addAction(saccount, raccount, amount);
                            this.dao.save((Object)senderAccount);
                            this.adao.save((Object)receiverAccount);
                            response.setResponseMessage("Rs." + amount + " successfully transferred to account " + receiverAccount.getAccno());
                            response.setTransferStatus(flag);
                        }
                        else {
                            flag = false;
                            response.setResponseMessage("This feature is not available for your account");
                            response.setTransferStatus(flag);
                        }
                    }
                    else {
                        flag = false;
                        response.setResponseMessage("Insufficient funds to complete the transfer");
                        response.setTransferStatus(flag);
                    }
                }
                else {
                    flag = false;
                    response.setResponseMessage("sender and recieiver accounts are same");
                    response.setTransferStatus(flag);
                }
            }
            else {
                final Saccount receiverAccount2 = this.dao.findByAccno(raccount);
                if (senderAccount.getAccno() != receiverAccount2.getAccno()) {
                    if (senderAccount.getBalance() > amount) {
                        final User user = this.udao.findByUsername(senderAccount.getUsername());
                        if (user.getFeatureStatus() == 3) {
                            senderAccount.setBalance(senderAccount.getBalance() - amount);
                            receiverAccount2.setBalance(receiverAccount2.getBalance() + amount);
                            this.tservice.addAction(saccount, raccount, amount);
                            this.dao.save((Object)senderAccount);
                            this.dao.save((Object)receiverAccount2);
                            response.setResponseMessage("Rs." + amount + " successfully transferred to account " + receiverAccount2.getAccno());
                            response.setTransferStatus(flag);
                        }
                        else {
                            flag = false;
                            response.setResponseMessage("This function isnt available for the account");
                            response.setTransferStatus(flag);
                        }
                    }
                    else {
                        flag = false;
                        response.setResponseMessage("Insufficient funds to complete the transfer");
                        response.setTransferStatus(flag);
                    }
                }
                else {
                    flag = false;
                    response.setResponseMessage("sender and recieiver accounts are same");
                    response.setTransferStatus(flag);
                }
            }
        }
        catch (Exception e) {
            flag = false;
            response.setResponseMessage("Account number is incorrect");
            response.setTransferStatus(flag);
        }
        response.setSaccount(saccount);
        return response;
    }
    
    public static boolean isprimary(final long account) {
        final String s = Long.toString(account).substring(0, 10);
        final String check = "3914918201";
        return s.equals(check);
    }
}
