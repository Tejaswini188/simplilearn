import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-listmovie',
  templateUrl: './listmovie.component.html',
  styleUrls: ['./listmovie.component.css']
})
export class ListmovieComponent implements OnInit {

    
  currentUser: UserDetails;
  movieList:any;
  movieStatus:boolean=false;
  statusName:String;
  statusNumber:number=0;

  constructor(
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService
    ) { 
    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {

    this.listMovie();
    //this.statuChange();
    //console.log();
  }


  statuChange(status:number,id:number){

    if(status===1)
    {
      status=0;
      this.statusName="Disabled";
    }
    else{
      status=1;
      this.statusName="Enabled";
    }

  

    console.log(status);


    this.cinemaService.updateMovieStatus(status,id).subscribe(resoponse=>{
        this.listMovie();

    },
    error=>{

      this.alertService.error(error.error.message);
      this. clearAlert();

    });   

  }

  listMovie(){

    this.cinemaService.listMovieData().subscribe((response)=>{

      this.movieList=response;
    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }


  deleteMovie(id:any){

    this.cinemaService.deleteMovie(id).subscribe(response=>{
      this.listMovie();
      
      //console.log(response.message);

      this.alertService.success(response.message);
         
    }, error=>{
      //console.log(error.error.message);
      
      this.alertService.error(error.error.message);

    });
  }


  clearAlert(){

    setTimeout(() =>  {
      //this.router.navigate(['/']);
      this.alertService.clear();
      },
  1000);
  }

}
