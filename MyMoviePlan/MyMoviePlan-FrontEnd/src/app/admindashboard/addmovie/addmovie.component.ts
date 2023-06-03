import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-addmovie',
  templateUrl: './addmovie.component.html',
  styleUrls: ['./addmovie.component.css']
})
export class AddmovieComponent implements OnInit {

  addmovieForm: FormGroup;
  loading = false;
  submitted = false;
  currentUser: UserDetails;

  id: string;
  isAddMode: boolean;
  movieList:any = [];
  movieDetailsSave:any;
  languageData:any;
  movieDetailsUpdate:any;
  message: any;
  fileToUpload: File ;
  msg: String;
  url: any; 
  imageName:String;
  genreList:any;
  genreData: any;
currentFile: File ;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService
    ) { 

      this.currentUser = this.authenticationService.currentUserValue;
      
    }

  ngOnInit(): void {

    this.addmovieForm = this.formBuilder.group({
      moviename: ['', Validators.required],
      description:['', Validators.required],
      language: ['', Validators.required],
      genre: ['', Validators.required],
      image: ['', Validators.required]
      });

      this.listGenre();
  }

  get f() { return this.addmovieForm.controls; }


     //handling file selection
onFileChanged(event: Event) {

  
  const target= event.target as HTMLInputElement;
  this.fileToUpload= (target.files as FileList)[0];

  if(!this.fileToUpload || this.fileToUpload.size==0) {
    this.msg = 'You must select an image';
    return;
  }
  
  let mimeType = this.fileToUpload.type;
  
  
    if (mimeType.match('image.jpeg') == null ) {
    this.msg = "Only jpeg and jpg images are supported";
    return;
  
   }

   var reader = new FileReader();
  reader.readAsDataURL(this.fileToUpload);
  
  this.imageName=this.fileToUpload.name;
  
  
  reader.onload = (_event) => {
    this.msg = "";
    this.url = reader.result; 
  
   
  }

 }
  onSubmit(){
    this.submitted = true;
    
    // stop here if form is invalid
    if (this.addmovieForm.invalid) {
        return;
    }

    this.loading = true;
    
  
   this.upload() ;

  }
  upload() {

    this.currentFile = this.fileToUpload;
    this.cinemaService.upload(this.currentFile, this.currentFile.name).subscribe(
      response => {

        this.message=response;
        console.log(response.id);
        

        this.movieDetailsSave={ 
          moviename: this.f['moviename'].value, 
          description: this.f['description'].value, 
          language:this.f['language'].value,
          genre: {name: this.genreData.genre.name, description:this.genreData.genre.description },
          image_id:response.id,
          name:response.name,
          type:response.type,
          data:response.data
       };

       this.saveMovie(this.movieDetailsSave);

        //this.loading = false;
      },
      err => {

        this.alertService.error(err.error.message);
        this.loading = false;
 
      });
   
  }
  
  listGenre(){

    this.cinemaService.listGenreData().subscribe((response)=>{

      this.genreList=response;
    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }



  saveMovie(movie: any){
    this.cinemaService.saveMovie(movie).subscribe(response=>{
      this.message=response.message;
      this.loading = false;
      this.alertService.success(response.message);
      //console.log(response.message);
    

      this.clearForm();
      this.loading = false;
    }, error=>{
      //console.log(error.error.message);

      this.alertService.error(error.error.message);
      
      this.message = error.error.message;
      this.loading = false;
    });
  
  }

  clearForm(){

    /*Clears form field and errors*/ 
    this.addmovieForm.reset();
    Object.keys(this.addmovieForm.controls).forEach(key => {
    this.addmovieForm.controls[key].setErrors(null);
    });
    /* Clears form alerts*/
    setTimeout(() =>  {
        //this.router.navigate(['/']);
        this.alertService.clear();
        this.router.navigate(['/listmovie']);
    },
    2000);

    this.url ='';
  }


}
