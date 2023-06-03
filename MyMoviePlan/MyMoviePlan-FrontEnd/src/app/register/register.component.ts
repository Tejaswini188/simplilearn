import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';
import { UserService } from '../_services/user.service';

/**
 *
 * @param form
 */

 function passwordsMatchValidator(form:any) {
    const password = form.get('password')
    const confirmPassword = form.get('confirmPassword')
  
    if(password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordsMatch: true })
    } else {
      confirmPassword.setErrors(null)
    }
  
    return null
  }
  
  /**
   * If the data is valid return null else return an object
   */
  function symbolValidator(control:any) { //control = registerForm.get('password')
    if(control.hasError('required')) return null;
    if(control.hasError('minlength')) return null;
  
    if(control.value.indexOf('@') > -1) {
      return null
    } else {
      return { symbol: true }
    }
  }

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  signupCredentials :any;

  constructor(
      private formBuilder: FormBuilder,
      private router: Router,
      private authenticationService: AuthenticationService,
      private userService: UserService,
      private alertService: AlertService
  ) {
      // redirect to home if already logged in
      if (this.authenticationService.currentUserValue) {
          this.router.navigate(['/']);
      }
  }

  ngOnInit() {
      this.registerForm = this.formBuilder.group({
          firstname: ['', Validators.required],
          lastname: ['', Validators.required],
          username: ['', Validators.required],
          password: ['', [Validators.required, Validators.minLength(6)]],
          //password: ['', [Validators.required, symbolValidator, Validators.minLength(4)]],
          confirmPassword: '',
          email:['', [Validators.required, Validators.email, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
          address:['', Validators.required],
          city:['', Validators.required],
          country:['', Validators.required]         
      },
       {
        validators: passwordsMatchValidator
       }
      );

      let testdata={
        "username":"user2",
        "email":"user2@gmail.com",
        "password":"user2",
        "role":["user"],
        "firstname":"victor",
        "lastname":"machel",
        "address":"stima plaza",
        "city":"nairobi",
        "country":"kenya"  
      };
  }
   
  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
      this.submitted = true;

      // reset alerts on submit
      this.alertService.clear();
      
   

      // stop here if form is invalid
      if (this.registerForm.invalid) {
          return;
      }

      this.loading = true;
      this.signupCredentials={ 
       username: this.f['username'].value,
       email: this.f['email'].value ,
       password: this.f['password'].value,
       role:["user"],
       firstname: this.f['firstname'].value,
       lastname: this.f['lastname'].value,
       address: this.f['address'].value,
       city: this.f['city'].value,
       country: this.f['country'].value
     };

        //console.log(this.signupCredentials);
      this.userService.register(this.signupCredentials)
          .pipe(first())
          .subscribe(
              data => {
                  console.log(data);
                  this.alertService.success('Registration successful', true);
                  this.router.navigate(['/login']);
                  this.clearAlert();
              },
              error => {
                  this.alertService.error(error.error.message);
                  this.clearAlert();
                  this.loading = false;
              }); 
  }

            clearAlert(){
                setTimeout(() =>  {
                    this.alertService.clear();
                },
                2000);
            }
            

}
