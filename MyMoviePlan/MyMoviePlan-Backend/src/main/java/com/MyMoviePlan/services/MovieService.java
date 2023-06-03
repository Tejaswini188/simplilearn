package com.MyMoviePlan.services;

import java.io.ByteArrayOutputStream;
import org.springframework.data.domain.Sort;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyMoviePlan.model.Genre;
import com.MyMoviePlan.model.Movie;
import com.MyMoviePlan.repository.GenreRepository;
import com.MyMoviePlan.repository.MovieRepository;





@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	GenreRepository genreRepository;
	
	public void saveMovie(Movie movie) {
		
		   			
		  Genre genre= genreRepository.findByNameIgnoreCase(movie.getGenre().getName());
			
		   movie.setGenre(genre);				
		
		   movieRepository.save(movie);
	}
	
	public List<Movie> listEnabledMovies() {
		//List<Movie> movies = new ArrayList<Movie>();
		//movieRepository.findAll().forEach(movie -> movies.add(movie));
		//return movies;
		return movieRepository.findAllMovieEnabled(Sort.by(Sort.Direction.ASC,"moviename"));
	
	}
	public List<Movie> listMovies() {
		//List<Movie> movies = new ArrayList<Movie>();
		//movieRepository.findAll().forEach(movie -> movies.add(movie));
		//return movies;
		return movieRepository.findAll();
	
	}
	
	
	public Optional<Movie> getMovie(int id) {
		return movieRepository.findById(id);
	}
	
	
	public void deleteMovie(int id) {
		
		if(!movieRepository.findById(id).equals(Optional.empty()))
		{		
		  movieRepository.deleteById(id);
		}
	}
	
	public Movie updateMovie(int id) {
		
		return movieRepository.findById(id).get();
				
	}
	
	
	  public List<Movie> getMovieByName(String name){
		
		return movieRepository.findByMovienameStartingWithEnabled(name,Sort.by(Sort.Direction.ASC,"moviename"));
		
		}
	
       public List<Movie> getMovieByGenre(String name){
    	   
    	 return movieRepository.findByGenreAndSort(name,Sort.by(Sort.Direction.ASC,"moviename"));
		
		}
       
       
       public List<Movie> getMovieByLanguage(String name){
    	   
      	 return movieRepository.findAllMovieByLanguageEnabled(name,Sort.by(Sort.Direction.ASC,"moviename"));
  		
  		}
       
   	public int changeMovieStatus(int status, int id) {
   		
   		return movieRepository.setStatusForMovie(status, id);
   	}
	
	
	// compress the image bytes before storing it in the database
		public static byte[] compressZLib(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

			return outputStream.toByteArray();
		}

		// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressZLib(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
	
}
