import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-editgenre',
  templateUrl: './editgenre.component.html',
  styleUrls: ['./editgenre.component.css']
})
export class EditgenreComponent implements OnInit {

  editgenreForm: FormGroup;
  loading = false;
  submitted = false;
  currentUser: UserDetails;
  genre: any;
  id: number;
  genreList:any = [];
  genreDetailsSave:any;
  genreDetailsUpdate:any;
  message: any;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService,
    private router: Router,
    private route: ActivatedRoute
  ) { 

    this.currentUser = this.authenticationService.currentUserValue;
  }

  ngOnInit(): void {

    this.editgenreForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
           })

      this.route.params.subscribe(params => {
        this.id = params['id']; 
        //console.log(this.id);
       this.getGenre(this.id); 
                         
          });
  }

  get f() { return this.editgenreForm.controls; }

 
  onSubmit(){
    this.submitted = true;
    
    // stop here if form is invalid
    if (this.editgenreForm.invalid) {
        return;
    }

    this.loading = true;
    
    this.genreDetailsSave={ 
      id:this.id,
      name: this.f['name'].value, 
      description: this.f['description'].value
       };

   console.log(this.genreDetailsSave);

   //console.log(this.genreDetailsSave);
   this.editGenre(this.genreDetailsSave);

  }

  //get specific product
getGenre(genreId: number){
  this.cinemaService.getGenre(genreId).subscribe((response)=>{
  
    this.genre=response;

    //console.log( this.product);
    this.editgenreForm.get('name')!.setValue(this.genre.name);
    this.editgenreForm.get('description')!.setValue(this.genre.description);
        
    //console.log(this.product);
  },(error=>{
    console.log(error.error.message);


    this.alertService.error(error.error.message);
    
    this.message = error.error.message;
    this.loading = false;
  }));
  }

  clearAlert(){

    setTimeout(() =>  {
      //this.router.navigate(['/']);
      this.alertService.clear();
      this.router.navigate(['/listgenre']);
  },
  2000);
  }


  editGenre(genre: any){

    //console.log(genre);

   this.cinemaService.updateGenre(genre).subscribe(response=>{
      //this.listProducts();
      this.message=response;
      console.log(this.message.message);
      this.loading = false;

      this.alertService.success(this.message.message);

      this.clearAlert();
      
    }, error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
      this.loading = false;
    });
    }




}
