import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-addgenre',
  templateUrl: './addgenre.component.html',
  styleUrls: ['./addgenre.component.css']
})
export class AddgenreComponent implements OnInit {

  addgenreForm: FormGroup;
  loading = false;
  submitted = false;
  currentUser: UserDetails;

  genreList:any = [];
  genreDetailsSave:any;
  genreDetailsUpdate:any;
  message: any;
 
  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private router: Router,
    private cinemaService: CinemaService
    ) { 

      this.currentUser = this.authenticationService.currentUserValue;
    }

  ngOnInit(): void {

    this.addgenreForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
     
      })
  }

  get f() { return this.addgenreForm.controls; }

 
  onSubmit(){
    this.submitted = true;
    
    // stop here if form is invalid
    if (this.addgenreForm.invalid) {
        return;
    }

    this.loading = true;
    
    this.genreDetailsSave={ 
      name: this.f['name'].value, 
      description: this.f['description'].value
       };

   console.log(this.genreDetailsSave);

   this.saveGenre(this.genreDetailsSave);


  }


  saveGenre(genre: any){
    this.cinemaService.saveGenre(genre).subscribe(response=>{
      this.message=response.message;
      this.loading = false;
      console.log(response.message);
      this.alertService.success(response.message);
      this.clearForm();

    }, error=>{
      console.log(error.error.message);

      this.alertService.error(error.error.message);
      
      this.message = error.error.message;
      this.loading = false;
    });
  
  }

  clearForm(){

    /*Clears form field and errors*/ 
    this.addgenreForm.reset();
    Object.keys(this.addgenreForm.controls).forEach(key => {
    this.addgenreForm.controls[key].setErrors(null);
    });
    /* Clears form alerts*/
    setTimeout(() =>  {
        this.alertService.clear();
        this.router.navigate(['/listgenre']);
    },
    2000);
  }


}
