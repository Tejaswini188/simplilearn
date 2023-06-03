import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Show } from 'src/app/_models/show';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';



@Component({
  selector: 'app-show-list',
  templateUrl: './show-list.component.html',
  styleUrls: ['./show-list.component.css']
})
export class ShowListComponent implements OnInit {
  //route:listshows
  currentUser: UserDetails;
  showList:any;
  moviename:string;
  id:number;
 
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
      this.id = params['id']; 
      console.log(this.id);
     this.getShowsById(this.id); 

        });

        //console.log(this.moviename);
        /*
        if(typeof this.moviename =='undefined' && !this.moviename ){
            //this.listShow(); 
        }*/
  }

  
  listShow(){

    this.cinemaService.listShowData().subscribe((response)=>{

      this.showList=response;
    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }


  getShowsByMovie(movie: string){
    this.cinemaService.getShowsByMovie(movie).subscribe((response)=>{

      this.showList=response;
     
      if(this.showList.length==0 && typeof this.moviename !='undefined' && this.moviename ){
        this.alertService.error("There is no show for " +this.moviename);
        this.clearAlert();
        
      } 
    },(error=>{
      //console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }

  getShowsById(id: number){
    this.cinemaService.getShow(id).subscribe((response)=>{

      this.showList=response;
      console.log(this.showList)
     
      if(this.showList.length==0 && typeof this.moviename !='undefined' && this.moviename ){
        this.alertService.error("There is no show for " +this.moviename);
        this.clearAlert();
        
      } 
    },(error=>{
      //console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }


  clearAlert(){
    setTimeout(() =>  {
      //this.router.navigate(['/']);
      this.alertService.clear();
      this.router.navigate(['/home']);
  },
  2000);
  }



}
