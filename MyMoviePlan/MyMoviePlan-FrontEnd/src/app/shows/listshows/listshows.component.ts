import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';


@Component({
  selector: 'app-listshows',
  templateUrl: './listshows.component.html',
  styleUrls: ['./listshows.component.css']
})
export class ListshowsComponent implements OnInit {
  //route:moviedetails
  currentUser: UserDetails;
  showList:any;
  moviename:string;

  constructor(
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService,
    private route: ActivatedRoute,
    private router: Router
  ) { 

    this.currentUser = this.authenticationService.currentUserValue;

  }

  ngOnInit(): void {


    this.route.params.subscribe(params => {
      this.moviename = params['moviename']; 
      //console.log(this.id);
     this.getShowsByMovie(this.moviename);                      
        });

        console.log(this.moviename);

        if(typeof this.moviename =='undefined' && !this.moviename ){
            //this.listShow(); 
            console.log("No Show")
        }

    
  }


    
 /*listShow(){

    this.cinemaService.listShowData().subscribe((response)=>{

      this.showList=response;
    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }*/


  getShowsByMovie(movie: string){
    this.cinemaService.getShowsByMovie(movie).subscribe((response)=>{

      this.showList=response;
      console.log(this.showList);

      if(this.showList.length==0 && typeof this.moviename !='undefined' && this.moviename ){
        this.alertService.error("There is no show for " +this.moviename);
        
        setTimeout(() =>  {
          this.alertService.clear();
          this.router.navigate(['/']);
                      },
                      1000);
        
      }


    },(error=>{
      //console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }


}
