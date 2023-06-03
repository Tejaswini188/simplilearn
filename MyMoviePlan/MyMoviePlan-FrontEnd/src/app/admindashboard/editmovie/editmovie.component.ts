import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from 'src/app/_models/user.model';
import { AlertService } from 'src/app/_services/alert.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';
import { CinemaService } from 'src/app/_services/cinema.service';

@Component({
  selector: 'app-editmovie',
  templateUrl: './editmovie.component.html',
  styleUrls: ['./editmovie.component.css']
})
export class EditmovieComponent implements OnInit {


  editmovieForm: FormGroup;
  loading = false;
  submitted = false;
  currentUser: UserDetails;

  id: number;
  isAddMode: boolean;
  movieList:any = [];
  movieDetailsSave:any;
  movieDetailsUpdate:any;
  languageData:any;
  message: any;
  fileToUpload: File | null = null;
  msg: String;
  url: any; 
  imageName:String;
  movie:any;
  genreData: any;
  genreName: any;
  genreList:any;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private cinemaService: CinemaService
    ) { 

      this.currentUser = this.authenticationService.currentUserValue;
      
    }

  ngOnInit(): void {

    this.editmovieForm = this.formBuilder.group({
      moviename: ['', Validators.required],
      description:['', Validators.required],
      genre: ['', Validators.required],
      language:['', Validators.required]
      //image: ['', Validators.required]
      });


      this.route.params.subscribe(params => {
        this.id = params['id']; 
        //console.log(this.id);
       this.getMovie(this.id);                      
          });
          this.listGenre();  

    
  }

  get f() { return this.editmovieForm.controls; }

  get selectedOption() {
    return this.genreList
      .find((c: { name: any; }) => c.name === this.genreData.name) || null;
  }


     //handling file selection
onFileChanged(event: Event) {

  //this.loading = !this.loading;
  const target= event.target as HTMLInputElement;
  this.fileToUpload= (target.files as FileList)[0];

  if(!this.fileToUpload || this.fileToUpload.size==0) {
    this.msg = 'You must select an image';
    return;
  }
  
  let mimeType = this.fileToUpload.type;
  //console.log(this.fileToUpload);
  
    if (mimeType.match('image.jpeg') == null ) {
    this.msg = "Only jpeg and jpg images are supported";
    return;
  
   }

   var reader = new FileReader();
  reader.readAsDataURL(this.fileToUpload);
  //console.log(this.fileToUpload.name);
  this.imageName=this.fileToUpload.name;
  //console.log(reader);
  
  reader.onload = (_event) => {
    this.msg = "";
    this.url = reader.result; 
  
   
  }

 }


  onSubmit(){
    this.submitted = true;
    
    // stop here if form is invalid
    if (this.editmovieForm.invalid) {
        return;
    }

    this.loading = true;
    
    //console.log(this.genreData);
    //this.genreData;
    this.movieDetailsSave={ 
      moviename: this.f['moviename'].value, 
      description: this.f['description'].value, 
      genre: {name: this.genreData.genre.name, description:this.genreData.genre.description },
      language:this.f['language'].value, 
      image_id:this.movie.image_id,
      name:this.movie.name,
      type:this.movie.type,
      data:this.movie.data
   };

   //console.log(this.movieDetailsSave.moviename);
   this.alertService.success(this.movieDetailsSave.moviename);

   this.updateMovie(this.movieDetailsSave,this.id);


  }

  getMovie(id: any){
    this.cinemaService.getMovie(id).subscribe((response)=>{

      this.movie=response;
      this.editmovieForm.get('moviename')!.setValue(this.movie.moviename);
      this.editmovieForm.get('description')!.setValue(this.movie.description);
      this.editmovieForm.get('language')!.setValue(this.movie.language);
      this.genreName=this.movie.genre.name;

      //console.log(this.genreData);

    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));

  }


  listGenre(){

    this.cinemaService.listGenreData().subscribe((response)=>{

      this.genreList=response;
    },(error=>{
      console.log(error.error.message);
      this.alertService.error(error.error.message);
     
    }));
  }


  updateMovie(movie: any, movieid: number){
    this.cinemaService.updateMovie(movie,movieid).subscribe(response=>{
      this.message=response.message;
      this.loading = false;
      this.alertService.success(response.message);
      //console.log(response.message);
      //this.router.navigate(['/dashboard']);

      this.clearAlert();
    }, error=>{
      console.log(error.error.message);

      this.alertService.error(error.error.message);
      
      this.message = error.error.message;
      this.loading = false;
    });
  
  }

   clearAlert(){

    setTimeout(() =>  {
      //this.router.navigate(['/']);
      this.alertService.clear();
      this.url ='';
      this.router.navigate(['/listmovie']);
  },
  2000);
  }

}
