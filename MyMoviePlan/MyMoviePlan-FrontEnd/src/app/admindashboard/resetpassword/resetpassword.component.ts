import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';

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
};

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  resetForm: FormGroup;
  loading = false;
  submitted = false;
  currentUser: UserDetails;
  loginCredentials:any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private route:ActivatedRoute,
    private alertService: AlertService
  ) { 


    this.currentUser = this.authenticationService.currentUserValue;

    if (!this.authenticationService.currentUserValue) {
      //console.log(this.aunthenicationService.currentUserValue);
      this.router.navigate(['/login']);
       }

  }

  ngOnInit(): void {

    this.resetForm = this.formBuilder.group({
      password: ['', Validators.required],
      confirmPassword: '',
                },
                {
                 validators: passwordsMatchValidator
                }
        ); }

    // convenience getter for easy access to form fields
    get f() { return this.resetForm.controls; }

    onSubmit() {
      this.submitted = true;
      
 
      if (this.resetForm.invalid) {
        return;
    }
    this.loading = true;

    this.savePassword();
 
     
    }

    savePassword(){      
    this.loginCredentials={ username: this.currentUser.username, password: this.f['password'].value };
  
    //console.log(this.loginCredentials);
    //accessing service
   this.authenticationService.resetPassword(this.loginCredentials).subscribe((response)=>{
      //this.message=response.message;
      //console.log(response);

      this.alertService.success(response.message);
      this.loading = false;
      this.authenticationService.logout();
      this.router.navigate(['/'])
      
    },(error=>{
      //console.log(error.error.message);
      this.alertService.error(error.error.messagee);
      
     
             this.loading = false;
    }));
    }
 

}
