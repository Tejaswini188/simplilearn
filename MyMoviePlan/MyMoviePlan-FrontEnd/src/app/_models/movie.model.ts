export class Movie {
     id: number;
    moviename: string;
    description: string;
    image_id: string;
    name: string;
    type: string;
    data: File;
    genre: {
        id: number;
        name: string;
        description: string;
    } 
}
