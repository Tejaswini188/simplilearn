import { Component, OnInit } from '@angular/core';
import {TransferHistory} from '../_models/transferhistory'
import { TransferhistoryService } from '../transferhistory.service';

@Component({
  selector: 'app-transfer-history',
  templateUrl: './transfer-history.component.html',
  styleUrls: ['./transfer-history.component.css']
})
export class TransferHistoryComponent implements OnInit {

  private accNo:number = (localStorage.getItem("savingAccNo") || 0) as number;
  public transferList:Array<TransferHistory> = [];

  constructor(private transferService:TransferhistoryService) { }

  ngOnInit(): void {
    this.transferService.getTransferHistory(this.accNo.toString()).subscribe(res=>{
      this.transferList=res;
    });
  }

}
