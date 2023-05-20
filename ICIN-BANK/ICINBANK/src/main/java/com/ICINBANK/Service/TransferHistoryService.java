package com.ICINBANK.Service;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import com.ICINBANK.Entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import com.ICINBANK.Repository.TransferHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferHistoryService
{
    @Autowired
    private TransferHistoryRepository dao;
    
    public Transfer addAction(final long saccount, final long raccount, final int amount) {
        final LocalDate today = LocalDate.now();
        final Transfer transfer = new Transfer();
        transfer.setSaccount(saccount);
        transfer.setRaccount(raccount);
        transfer.setAmount(amount);
        transfer.setDate(today);
        return (Transfer)this.dao.save((Object)transfer);
    }
    
    public List<Transfer> getTransfers(final long account) {
        final List<Transfer> sender = (List<Transfer>)this.dao.findBySaccount(account);
        final List<Transfer> receiver = (List<Transfer>)this.dao.findByRaccount(account);
        final List<Transfer> merged = new ArrayList<Transfer>();
        merged.addAll(sender);
        merged.addAll(receiver);
        Collections.sort(merged);
        return merged;
    }
}
