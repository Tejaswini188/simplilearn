import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbCalendar, NgbDateAdapter, NgbTimeAdapter } from '@ng-bootstrap/ng-bootstrap';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-addshow',
  templateUrl: './addshow.component.html',
  styleUrls: ['./addshow.component.css']
})
export class AddshowComponent implements OnInit {
  addshowForm: FormGroup;
  loading = false;
  submitted = false;
  currentUser: UserDetails;
  id: string;
  isAddMode: boolean;
  showList:any = [];
  showDetailsSave:any;
  showDetailsUpdate:any;
  message: any;
  movieList:any;
  movieData: any;
  screenData:any;
  durationData:any;
  durationGen:any[]=[];
  theaterData:any;
  cityData:any;
  showData:any;
  seatData:number=200;
  countryData:string='INDIA';
  model2: string;
  model1: string;
  fullTime:string;
  rawfulltime:any;
  rawdate:any;


  time: '13:30:00';
 /* meridian = true;

  toggleMeridian() {
      this.meridian = !this.meridian;
  }*/

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService,
    private ngbCalendar: NgbCalendar,
    private dateAdapter: NgbDateAdapter<string>
  ) { 

    this.currentUser = this.authenticationService.currentUserValue;
    
  }

  ngOnInit(): void {

    this.addshowForm = this.formBuilder.group({
      name: ['', Validators.required],
      showname:['', Validators.required],
      theater: ['', Validators.required],
      screen: ['', Validators.required],      
      seatstatus: [{value: '', disabled: true}, [Validators.required, Validators.pattern("^[0-9]*$")]],
      price: ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      location: ['', Validators.required],
      duration: ['', Validators.required],
      city: ['', Validators.required],
      country: [{value: '', disabled: true}, Validators.required],
      time: ['', Validators.required],
      date: ['', Validators.required]
      
   
         });    

         this.getDurationCalc();
         this.listEnabledMovies();

     
  }

  get today() {
    return this.dateAdapter.toModel(this.ngbCalendar.getToday())!;
  }


  get f() { return this.addshowForm.controls; }


  onSubmit(){
    this.submitted = true;
    
    // stop here if form is invalid
    if (this.addshowForm.invalid) {
        return;
    }

    this.loading = true;
       

    this.rawfulltime={time:this.f['time'].value};
    this.rawdate=this.f['date'].value;
    

    this.timePMAMconversion(this.rawfulltime.time);
    this.dateLeadingZeroConconversion(this.rawdate);
   
    

    this.showDetailsSave={ 
      name: this.f['name'].value, 
      showname: this.f['showname'].value, 
      movie: {name: this.movieData.movie.name, 
              moviename: this.movieData.movie.moviename, 
              description:this.movieData.movie.description,
              image_id:this.movieData.movie.image_id,
              data:this.movieData.movie.data,
              type:this.movieData.movie.type,
              genre:{ 
                name:this.movieData.movie.genre.name,
                description:this.movieData.movie.genre.description             
              }            
            },
      price:this.f['price'].value, 
      theater:this.f['theater'].value, 
      screen:this.f['screen'].value, 
      seatstatus:this.f['seatstatus'].value, 
      location:this.f['location'].value,      
      city:this.f['city'].value, 
      country:this.f['country'].value, 
      duration:this.f['duration'].value, 
      date:this.rawdate,
      time:this.fullTime
    };

   

   this.saveShow(this.showDetailsSave);

  }

  timePMAMconversion(timeData: string){
    const pad = (i: number): string => i < 10 ? `0${i}` : `${i}`;
    let times=timeData.split(':',3);
    let hr=parseInt(times[0], 10);
    let min=parseInt(times[1], 10);
    let sec=parseInt(times[2], 10);
    let ampm=hr>=12?"PM":"AM";
    let pmam;

    if (ampm==="PM"){
         pmam=(hr>12)?hr-12:hr;
         }else{
         pmam=hr==0?hr+12:hr;
        }     
    this.fullTime=`${pad(pmam)}:${pad(min)}:${pad(sec)} ${ampm}` ;
  }

  dateLeadingZeroConconversion(dateConvt: any){
    const pad = (i: number): string => i < 10 ? `0${i}` : `${i}`;
    let dates=dateConvt.split('/',3);
    let dd=parseInt(dates[0], 10);
    let mm=parseInt(dates[1], 10);
    let yyyy=parseInt(dates[2], 10);

    this.rawdate=`${pad(dd)}/${pad(mm)}/${pad(yyyy)}` ;

  }


  getDurationCalc(){
      let hrsize=4;
      let minsize=60;
        for(let j=1;j<=hrsize;j++){          
            for(let i=0;i<minsize;i++){
              if(i%5==0){  
                this.durationGen.push(`${j} hr  ${i} min`);
                 //console.log(`${j} hr  ${i} min`);
                }
            }          
          }  
          
          //console.log(this.durationGen)
     }

     listEnabledMovies(){

      this.cinemaService.listEnabledMovies().subscribe((response)=>{
  
        this.movieList=response;
      },(error=>{
        //console.log(error.error.message);
        this.alertService.error(error.error.message);
       
      }));
    }

  
  saveShow(show: any){
    this.cinemaService.saveShow(show).subscribe(response=>{
      this.message=response.message;
      this.loading = false;
      this.alertService.success(response.message);
      
    

      this.clearForm();
    }, error=>{
      

      this.alertService.error(error.error.message);
      
      this.message = error.error.message;
      this.loading = false;
    });

      
  }

  clearForm(){

    /*Clears form field and errors*/ 
    this.addshowForm.reset();
    Object.keys(this.addshowForm.controls).forEach(key => {
    this.addshowForm.controls[key].setErrors(null);
    });
    /* Clears form alerts*/
    setTimeout(() =>  {
        //this.router.navigate(['/']);
        this.alertService.clear();
        this.router.navigate(['/listshow']);
    },
    2000);
  }


}
