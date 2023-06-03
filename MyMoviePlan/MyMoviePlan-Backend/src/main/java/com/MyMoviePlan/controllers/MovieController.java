package com.MyMoviePlan.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.MyMoviePlan.model.Genre;
import com.MyMoviePlan.model.Movie;
import com.MyMoviePlan.model.Order;
import com.MyMoviePlan.model.Seats;
import com.MyMoviePlan.model.Show;
import com.MyMoviePlan.request.GenreRequest;
import com.MyMoviePlan.request.MovieRequest;
import com.MyMoviePlan.request.ShowRequest;
import com.MyMoviePlan.response.MessageResponse;

import com.MyMoviePlan.services.GenreService;
import com.MyMoviePlan.services.MovieService;
import com.MyMoviePlan.services.OrderService;
import com.MyMoviePlan.services.ShowService;


@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	ShowService showService;
	
	
	@Autowired
	GenreService genreService;
	

	
    @Autowired
	OrderService orderService;
	  
	 
	


	  /*order*/
	  @PostMapping("/saveOrders")
	  private ResponseEntity<?> saveOrder(@RequestBody Order order) {
		 Order savedOrders=orderService.saveOrder(order);
			//return product.getId();
			return ResponseEntity.ok(savedOrders);
		} 
	  
	  @PostMapping("/saveSeats")
	  private ResponseEntity<?> saveSeats(@RequestBody Seats seat) {
		 Seats seats=showService.saveSeats(seat);
			//return product.getId();
			return ResponseEntity.ok(seats);
		} 
	  
	  
		@GetMapping("/listSeats")
		private ResponseEntity<?> geSeats() {
			List<Seats> seats=showService.getSeats();
			
			int arr[]=new int[seats.size()];
			for (int i = 0; i < seats.size(); i++) {
				  
				arr[i]=seats.get(i).getSeatnumber();
		        }		
	
			return ResponseEntity.ok(arr);
		}
		
		@GetMapping("/listSeatsByShowId/{id}")
		private ResponseEntity<?> geSeatsByShowId(@PathVariable("id")Integer id) {
			List<Seats> seats=showService.getSeatsByShowId(id);
			
			int arr[]=new int[seats.size()];
			for (int i = 0; i < seats.size(); i++) {
				  
				arr[i]=seats.get(i).getSeatnumber();
		        }		
	
			return ResponseEntity.ok(arr);
		}
	  
	  /*order*/
	/*Genre functions*/
	@PostMapping("/saveGenre")
	// @RequestMapping(name="/movies", method=RequestMethod.POST)
	private ResponseEntity<?> saveProduct(@RequestBody Genre genre) {
		genreService.saveGenre(genre);
		//return product.getId();
		return ResponseEntity.ok(new MessageResponse(genre.getName() + " has been saved"));
	}
	
	@GetMapping("/listGenre")
	private ResponseEntity<?> getGenres() {
		List<Genre> genre=genreService.listGenre();
		return ResponseEntity.ok(genre);
	}

	@GetMapping("/getGenre/{id}")
	private ResponseEntity<?> getGenre(@PathVariable("id")Integer id) {
		Optional<Genre> genre=genreService.geGenre(id);
		return ResponseEntity.ok(genre);
	}
	
	@GetMapping("/getGenreName/{name}")
	private ResponseEntity<?> getGenreName(@PathVariable("name")String name) {
		List<Genre> genre=genreService.getGenreByName(name);
		return ResponseEntity.ok(genre);
	}
	
	@PutMapping("/updateGenre/{id}")
	private ResponseEntity<?> updateProducts(@PathVariable("id")Integer id,@RequestBody GenreRequest body ) {
		
		Genre currentGenre=genreService.updateGenre(id);
		currentGenre.setName(body.getName());
		currentGenre.setDescription(body.getDescription());
				
		genreService.saveGenre(currentGenre);
		
		//List<Product> products=productService.listProducts();
		return ResponseEntity.ok(new MessageResponse(currentGenre.getName() +" has been Updated"));
	}
	
	
	@DeleteMapping("/deleteGenre/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<?> deleteGenre(@PathVariable("id") Integer id) {
		genreService.deleteGenre(id);
		//System.out.println(id);
		return ResponseEntity.ok(new MessageResponse("Deleted"));
	}
	
	/*Movie functions*/

	@PostMapping("/saveMovie")
	// @RequestMapping(name="/movies", method=RequestMethod.POST)
	private ResponseEntity<?> saveProduct(@RequestBody Movie movie) {
		movieService.saveMovie(movie);
		//return product.getId();
		return ResponseEntity.ok(new MessageResponse(movie.getMoviename() + " has been saved"));
	}
	
	@GetMapping("/listEnabledMovies")
	private ResponseEntity<?> getEnabledMovies() {
		List<Movie> movie=movieService.listEnabledMovies();
		return ResponseEntity.ok(movie);
	}
	
	@GetMapping("/listMovies")
	private ResponseEntity<?> getMovies() {
		List<Movie> movie=movieService.listMovies();
		return ResponseEntity.ok(movie);
	}
	
	@GetMapping("/getMoviesByGenre/{name}")
	private ResponseEntity<?> getMoviesByGenre(@PathVariable("name")String name) {
		List<Movie> movie=movieService.getMovieByGenre(name);
		return ResponseEntity.ok(movie);
	}

	@GetMapping("/getMovie/{id}")
	private ResponseEntity<?> getMovie(@PathVariable("id")Integer id) {
		Optional<Movie> movie=movieService.getMovie(id);
		return ResponseEntity.ok(movie);
	}
	
	@GetMapping("/getMoviesName/{name}")
	private ResponseEntity<?> getMovieName(@PathVariable("name")String name) {
		List<Movie> movie=movieService.getMovieByName(name);
		return ResponseEntity.ok(movie);
	}
	
	@GetMapping("/getMoviesLanguage/{name}")
	private ResponseEntity<?> getMovieLanguage(@PathVariable("name")String name) {
		List<Movie> movie=movieService.getMovieByLanguage(name);
		return ResponseEntity.ok(movie);
	}
	
    @PutMapping("/updateMovie/{id}")
	private ResponseEntity<?> updateProducts(@PathVariable("id")Integer id,@RequestBody MovieRequest body ) {
		
		Movie currentMovie=movieService.updateMovie(id);
		currentMovie.setMoviename(body.getMoviename());
		currentMovie.setDescription(body.getDescription()); 
		currentMovie.setGenre(body.getGenre());
		currentMovie.setLanguage(body.getLanguage());
				
		movieService.saveMovie(currentMovie);
		
		//List<Product> products=productService.listProducts();
		return ResponseEntity.ok(new MessageResponse(currentMovie.getMoviename() +" has been edited"));
	}
    
    @PutMapping("/updateMovieStatus")
    private ResponseEntity<?> updateMovieStatus(@RequestParam int status,@RequestParam int id ){
    	
    	 movieService.changeMovieStatus(status,id);
    	 Optional<Movie> movie=movieService.getMovie(id);
    	
    	return ResponseEntity.ok(movie.get().getMoviestatus());
    }
	
	
	@DeleteMapping("/deleteMovie/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<?> deleteMovie(@PathVariable("id") Integer id) {
		movieService.deleteMovie(id);
		//System.out.println(id);
		return ResponseEntity.ok(new MessageResponse("Movie deleted"));
	}
	
	/*cartitem functions*/
	

	/*Show functions*/
	
	@PostMapping("/saveShow")
	// @RequestMapping(name="/movies", method=RequestMethod.POST)
	private ResponseEntity<?> saveShow(@RequestBody Show show) {
		showService.saveShow(show);
		//return product.getId();
		return ResponseEntity.ok(new MessageResponse( " has been saved"));
	}
	
	@GetMapping("/listShow")
	private ResponseEntity<?> getShows() {
		List<Show> show=showService.listShow();
		return ResponseEntity.ok(show);
	}

	@GetMapping("/getShow/{id}")
	private ResponseEntity<?> getShow(@PathVariable("id")Integer id) {
		Optional<Show> show=showService.getShow(id);
		return ResponseEntity.ok(show);
	}
	
	
	@GetMapping("/getShowsByMovie/{name}")
	private ResponseEntity<?> getShowsByGenre(@PathVariable("name")String name) {
		List<Show> show=showService.getShowsByMovie(name);
		return ResponseEntity.ok(show);
	}


	
	@PutMapping("/updateShow/{id}")//movie_id update
	private ResponseEntity<?> updateShows(@PathVariable("id")Integer id,@RequestBody ShowRequest body ) {
		
		Show currentShow=showService.updateShow(id);
		currentShow.setPrice(body.getPrice());
		currentShow.setDate(body.getDate());
		currentShow.setTime(body.getTime());
		currentShow.setSeatstatus(body.getSeatstatus());
		currentShow.setMovie(body.getMovie());
		currentShow.setCity(body.getCity());
		currentShow.setCountry(body.getCountry());
		currentShow.setLocation(body.getLocation());
		currentShow.setTheater(body.getTheater());
		currentShow.setDuration(body.getDuration());
		currentShow.setScreen(body.getScreen());
		currentShow.setShowname(body.getShowname());
				
		showService.saveShow(currentShow);
		
		return ResponseEntity.ok(new MessageResponse(" has been edited"));
	}
	
	
	@DeleteMapping("/deleteShow/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<?> deleteShow(@PathVariable("id") Integer id) {
	    Optional<Show> show=showService.getShow(id);
		showService.deleteShow(id);
		//System.out.println(id);
		return ResponseEntity.ok(new MessageResponse(show.get().getShowname() + "show has been deleted"));
	}
	


}
