import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../transaction.service';
import {Transaction} from '../_models/transaction';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {

  username:String=(localStorage.getItem("username") || "");
  accNo:number=+(localStorage.getItem("savingAccNo") || 0) as number;
  public transactionList:Array<Transaction> = [];
  public savingBalance:number = (0) as number;

  constructor(private transactionService:TransactionService) {}

  ngOnInit(): void {
      this.transactionService.getTransactions(this.accNo.toString()).subscribe(res=>{
        this.transactionList = res;
        console.log(this.transactionList);
    });
    this.transactionService.getSavingAccount(this.username as string).subscribe(res=>{
      this.savingBalance = res.balance;
    });
    
  }

}
