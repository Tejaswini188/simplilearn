import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-listshow',
  templateUrl: './listshow.component.html',
  styleUrls: ['./listshow.component.css']
})
export class ListshowComponent implements OnInit {
  //route:listshow
  currentUser: UserDetails;
  showList:any;

  constructor(
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService
  ) { 

    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {

    this.listShow();
  }

  
  listShow(){

    this.cinemaService.listShowData().subscribe((response)=>{

      this.showList=response;
    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }


  deleteShow(id:any){

    this.cinemaService.deleteShow(id).subscribe(response=>{
      this.listShow();
      
      //console.log(response.message);

      this.alertService.success(response.message);
      
    }, error=>{
      //console.log(error.error.message);
      
      this.alertService.error(error.error.message);

    });
  }

  changeStatusShow(){}

}
