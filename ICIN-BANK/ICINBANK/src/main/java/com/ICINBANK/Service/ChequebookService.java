package com.ICINBANK.Service;

import com.ICINBANK.Entity.Saccount;
import com.ICINBANK.Entity.Account;
import java.util.List;
import java.time.LocalDate;
import com.ICINBANK.Response.ChequeResponse;
import com.ICINBANK.Entity.ChequebookRequest;
import com.ICINBANK.Repository.SaccountRepository;
import com.ICINBANK.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ICINBANK.Repository.ChequeBookRepository;
import org.springframework.stereotype.Service;

@Service
public class ChequebookService
{
    @Autowired
    private ChequeBookRepository dao;
    @Autowired
    private AccountRepository adao;
    @Autowired
    private SaccountRepository sdao;
    
    public ChequeResponse createrequest(final ChequebookRequest chequebook) {
        final ChequeResponse response = new ChequeResponse();
        final long account = chequebook.getAccount();
        final List<ChequebookRequest> prevRequests = (List<ChequebookRequest>)this.dao.findByAccount(account);
        if (!prevRequests.isEmpty()) {
            for (int i = 0; i < prevRequests.size(); ++i) {
                if (!prevRequests.get(i).isRequestStatus()) {
                    response.setResponseMessage("Your previous chequebook request is still pending.");
                    response.setStatus(false);
                    response.setAccount(account);
                    return response;
                }
            }
        }
        final LocalDate today = LocalDate.now();
        if (isprimary(account)) {
            try {
                final Account account2 = this.adao.findByAccno(account);
                response.setAccount(account2.getAccno());
                response.setStatus(true);
                response.setResponseMessage("Request submitted successfully");
                chequebook.setAccType("Primary");
                chequebook.setDate(today);
                chequebook.setRequestStatus(false);
                this.dao.save((Object)chequebook);
            }
            catch (Exception e) {
                response.setAccount(account);
                response.setStatus(false);
                response.setResponseMessage("account number is incorrect");
            }
        }
        else if (isSecondary(account)) {
            try {
                final Saccount saccount = this.sdao.findByAccno(account);
                response.setAccount(saccount.getAccno());
                response.setStatus(true);
                response.setResponseMessage("Request submitted successfully");
                chequebook.setRequestStatus(false);
                chequebook.setAccType("Secondary");
                chequebook.setDate(today);
                this.dao.save((Object)chequebook);
            }
            catch (Exception e) {
                response.setAccount(account);
                response.setStatus(false);
                response.setResponseMessage("account number is incorrect");
            }
        }
        else {
            response.setAccount(account);
            response.setStatus(false);
            response.setResponseMessage("account number is incorrect");
        }
        return response;
    }
    
    public List<ChequebookRequest> getRequests(final long account) {
        return (List<ChequebookRequest>)this.dao.findByAccount(account);
    }
    
    public static boolean isprimary(final long account) {
        final String s = Long.toString(account).substring(0, 10);
        final String check = "3914918201";
        return s.equals(check);
    }
    
    public static boolean isSecondary(final long account) {
        final String s = Long.toString(account).substring(0, 10);
        final String check = "3914918202";
        return s.equals(check);
    }
}
