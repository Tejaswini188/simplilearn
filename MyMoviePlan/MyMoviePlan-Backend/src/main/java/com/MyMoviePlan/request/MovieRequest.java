package com.MyMoviePlan.request;

import com.MyMoviePlan.model.Genre;


public class MovieRequest {

	private String moviename;
	private String name;
	private Genre genre;
	private String description;
    private String image_id;
	private String type;
	private String language;
	private byte[] data;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description.toUpperCase();
	}

	public void setDescription(String description) {
		this.description = description.toUpperCase();
	}

	public String getMoviename() {
		return moviename.toUpperCase();
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename.toUpperCase();
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
}
