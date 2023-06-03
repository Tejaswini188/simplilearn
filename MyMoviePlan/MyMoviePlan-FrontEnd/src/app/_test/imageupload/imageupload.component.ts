import { HttpClient, HttpEvent, HttpEventType, HttpRequest, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-imageupload',
  templateUrl: './imageupload.component.html',
  styleUrls: ['./imageupload.component.css']
})
export class ImageuploadComponent implements OnInit {

  constructor(
    private httpClient: HttpClient,
    private alertService: AlertService,
    private authenticationService: AuthenticationService,
    private cinemaService: CinemaService
    ) {
      this.currentUser = this.authenticationService.currentUserValue;


     }
  currentUser: UserDetails;
  selectedFile: File;
  imgURL: any;
  retrievedImage: any;
  retrievedImages: any=[];
  base64Data: any;
  base64DataRes: any=[];
  retrieveResonse: any;
  retrieveImagesRes:any=[];
  //message: string;
  imageName: any;


  currentFile: File;
  progress = 0;
  message = '';
  fileInfos: Observable<any>;
  listImages: Observable<any>;

  ngOnInit(): void {

    this.fileInfos = this.cinemaService.getFiles();
    this.getImages();
   }

  public selectFile(event: Event) {
    //Select File
    const target= event.target as HTMLInputElement;
    this.selectedFile = (target.files as FileList)[0];
  }


  upload() {

    this.currentFile = this.selectedFile;
    this.cinemaService.upload(this.currentFile, this.currentFile.name).subscribe(
      event => {
   
        this.message = 'Image Is uploaded successfully';
        this.alertService.success(this.message);
      },
      err => {

        this.alertService.error(err.error.message);

        /*
        this.progress = 0;
        this.message = 'Could not upload the file!'; */
        //this.currentFile = undefined;
      });
    //this.selectedFile = undefined;
  }

  getImageByName(){
    this.cinemaService.getImage(this.imageName).subscribe(
      response=>{
        this.retrieveResonse = response;
        this.base64Data = this.retrieveResonse.data;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        this.alertService.success(this.retrieveResonse.message );
      },
      error=>{

        this.alertService.error( error.error.message);
        this.retrievedImage="";
      }
    );
  }

  getImages(){

    this.cinemaService.getImages().subscribe(
      response=>{
        this.retrieveImagesRes = response;
        this.base64DataRes = this.retrieveImagesRes;

       /* this.base64DataRes.forEach( (x:any) => {
          this.retrievedImages.push('data:image/jpeg;base64,' +x.data);
          console.log(this.retrievedImages);
        }); */
      },
      error=>{

        this.alertService.error( error.error.message);
        this.retrievedImages="";
      }
    );

  }
}
