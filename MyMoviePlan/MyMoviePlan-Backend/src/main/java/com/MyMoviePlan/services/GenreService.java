package com.MyMoviePlan.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyMoviePlan.model.Genre;
import com.MyMoviePlan.repository.GenreRepository;





@Service
public class GenreService {

	@Autowired
	GenreRepository genreRepository;
	
	public void saveGenre(Genre genre) {
		genreRepository.save(genre);
	}
	
	public List<Genre> listGenre() {
		List<Genre> genres = new ArrayList<Genre>();
		genreRepository.findAll().forEach(genre -> genres.add(genre));
		return genres;
	}
	
	public Optional<Genre> geGenre(int id) {
		return genreRepository.findById(id);
	}
	
	
	public void deleteGenre(int id) {
		
		if(!genreRepository.findById(id).equals(Optional.empty()))
		{		
			genreRepository.deleteById(id);
		}
	}
	
	public Genre updateGenre(int id) {
		
		return genreRepository.findById(id).get();
				
	}
	
	
	public List<Genre> getGenreByName(String name){
		
		return genreRepository.findByName(name);
		
		}
	
	
	
}
