import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { Roles, UserDetails } from '../_models/user.model';
import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    loginCredentials:any;
    userDetails: UserDetails;
    public userRoles = Roles;
    
   
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService
    ) {
        // redirect to home if already logged in
        if (this.authenticationService.currentUserValue) {
            //console.log("1"+this.authenticationService.currentUserValue);
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // reset alerts on submit
        this.alertService.clear();

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        //this.loginCredentials={ username: this.f['username'].value, password: this.f['password'].value };

        let username=this.f['username'].value;
        let password=this.f['password'].value;
        this.loginCredentials={ username: this.f['username'].value, password: this.f['password'].value };

        this.loading = true;
        this.authenticationService.login2(this.loginCredentials)
            .pipe(first())
            .subscribe(
                data => {

                                                     
                    switch (data.roles[0]){
                        case this.userRoles.Admin : {
                            //console.log(this.userRoles.Admin);
                            //console.log("1 ");
                            
                            this.router.navigate(['/admin']);
                            break;
                        }
                        case this.userRoles.User : {
                           // console.log(this.userRoles.User);
                            //this.router.navigate([this.returnUrl]);
                            this.router.navigate(['/user']);
                            //console.log("2 ");
                            break;
                        }
                        case this.userRoles.Moderator : {
                           // console.log(this.userRoles.Moderator);
                            //this.router.navigate(['/moderator']);
                            
                            console.log("3 ");
                            break;
                        }
                        default: { 
                        //console.log(this.userRoles.User );
                            //statements; 
                            console.log("4");
                            break; 
                         } 
                    }


                    //
                    //console.log(this.returnUrl);

                    
                },
                error => {

                    console.log();
                    this.alertService.error(error.error.message);
                    this.loading = false;
                });
    }

}
