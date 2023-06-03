import { Component, Input, OnInit } from '@angular/core';
import { first } from 'rxjs';
import { User, UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';
import { CinemaService } from '../_services/cinema.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Input() radiovalue: string="movie";
  currentUser: UserDetails;
  users:any[] = [];
  searchText = '';
  movieList:any;
  movieListByName:any;

  constructor(
      private authenticationService: AuthenticationService,
      private userService: UserService,  
      private alertService: AlertService,
      private cinemaService: CinemaService
  ) {
      this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit() {
   
        this.listEnabledMovies();
        this.listMovieSearching();
         }
  
  
         listEnabledMovies(){

        this.cinemaService.listEnabledMovies().subscribe((response)=>{
    
          this.movieList=response;
              
        },(error=>{
          console.log(error.error.message);
          this.alertService.error(error.error.message);
         
        }));
      }


      listMovieSearching(){

        this.cinemaService.listEnabledMovies().subscribe((response)=>{   
          
          this.movieListByName=response;
      
        },(error=>{
          console.log(error.error.message);
          this.alertService.error(error.error.message);
         
        }));
      }


      searchOnKeyUp(event: Event) {

        const target= event.target as HTMLInputElement;
        let input = target.value;
        //console.log(); 
        input=input.trim();
        //console.log(input.length);
        
        if (input.length > 1) {

          if(this.radiovalue==="movie"){

            this.getMoviesByName(input);
          }

          if(this.radiovalue==="genre"){

            this.getMoviesByGenre(input);

            }  
            if(this.radiovalue==="language"){

              this.getMoviesByLanguage(input);
  
              }           
         
          //console.log(this.results);
        }else if (input.length===0){
          this.listMovieSearching();          
        }
        
      } 

  getMoviesByName(input: string){
    this.cinemaService.getMoviesByName(input).subscribe((response)=>{
      
      this.movieListByName=response;
      //console.log(this.movieListByName);
    },(error=>{
      //console.log(error.error.message);
      
      this.alertService.error(error.error.message);
    }));
  }

  getMoviesByLanguage(input: string){
    this.cinemaService.getMoviesByLanguage(input).subscribe((response)=>{
      
      this.movieListByName=response;
      //console.log(this.movieListByName);
    },(error=>{
      //console.log(error.error.message);
      
      this.alertService.error(error.error.message);
    }));
  }

  getMoviesByGenre(input:string){
    this.cinemaService.getMoviesByGenre(input).subscribe((response)=>{
      
      this.movieListByName=response;
      //console.log(this.movieListByName);
    },(error=>{
      console.log(error.error.message);
      
      this.alertService.error(error.error.message);
    }));
  }

}
