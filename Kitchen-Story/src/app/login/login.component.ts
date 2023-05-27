import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminsService } from './../admins.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public message: String;
  constructor(private adminService: AdminsService) {}
  ngOnInit() {}

  public login(id: number, password: String): void {
    this.adminService.login(id, password).subscribe(
      (data) => {
        console.log('success');
        alert('success');
      },
      (error) => {
        console.log(error);
        console.log('failed');
      }
    );
  }
  public changePassword(id: number, password: String): void {
    this.adminService.changePassword(id, password).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
