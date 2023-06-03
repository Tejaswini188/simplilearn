import { Movie } from "./movie.model";

export class Show {
    id:number;
    showname:string;
    movie: Movie;
    theater:string;
    screen:string;
    seatstatus:number;
    price:number;
    time:string;
    date:string;
    location:string;
    city: string;
    country:string;

    constructor(id:number,showname:string,movie:Movie,theater:string,screen:string,
        seatstatus:number,price:number,time:string,date:string,location:string,city:string,country:string){
            this.id=id;
            this.showname=showname;
            this.movie=movie;
            this.theater=theater;
            this.screen=screen;
            this.seatstatus=seatstatus;
            this.price=price;
            this.time=time;
            this.date=date;
            this.location=location;
            this.city=city;
            this.country=country;
        }
}
