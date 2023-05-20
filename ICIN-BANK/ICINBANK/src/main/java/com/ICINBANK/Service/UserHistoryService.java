package com.ICINBANK.Service;

import java.util.List;
import java.time.LocalDate;
import com.ICINBANK.Entity.UserHistory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ICINBANK.Repository.UserHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class UserHistoryService
{
    @Autowired
    private UserHistoryRepository dao;
    
    public UserHistory addAction(final long account, final int amount, final int balance, final String action) {
        final LocalDate today = LocalDate.now();
        final UserHistory row = new UserHistory();
        row.setAccount(account);
        row.setAction(action);
        row.setAmount(amount);
        row.setDate(today);
        return (UserHistory)this.dao.save((Object)row);
    }
    
    public List<UserHistory> getHistory(final long account) {
        return (List<UserHistory>)this.dao.findByAccount(account);
    }
}
