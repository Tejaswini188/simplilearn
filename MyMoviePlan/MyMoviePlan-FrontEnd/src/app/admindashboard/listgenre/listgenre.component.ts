import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-listgenre',
  templateUrl: './listgenre.component.html',
  styleUrls: ['./listgenre.component.css']
})
export class ListgenreComponent implements OnInit {
  
  currentUser: UserDetails;
  genreList:any;

  constructor(
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService
    ) { 
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {

    this.listGenre();
  }


  listGenre(){

    this.cinemaService.listGenreData().subscribe((response)=>{

      this.genreList=response;
    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }


  deleteGenre(id:any){

    this.cinemaService.deleteGenre(id).subscribe(response=>{
      this.listGenre();
      
      console.log(response.message);

      this.alertService.success(response.message);
      
    }, error=>{
      console.log(error.error.message);
      
      this.alertService.error(error.error.message);

    });
  }

}
