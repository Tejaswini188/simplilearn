package com.MyMoviePlan.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="movie_nms",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"moviename"})
})
public class Movie {

	@Id
	@GeneratedValue
	private int id; 
	
	@Column(unique=true)
	private String moviename;
	
	private String description;
	
	private String language;
	
	private String image_id;
	
	private String name;
	private String type;
	private int moviestatus;
	@Lob
	private byte[] data;
	//private String genre;
	
	 @Temporal(TemporalType.TIMESTAMP)
		@Column(nullable = false)
		@CreationTimestamp
		private Date created_at;
		
		@PrePersist
		private void onCreate() {
			created_at=new Date();
			
			}
	
	
	@OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name = "genre_id", referencedColumnName = "id")
	private Genre genre;
	 
	
	Movie(){}
	
	Movie(String moviename, String description,Genre genre,String image_id,String name, String type, byte[] data,
			int movistatus,String language){
		
		this.moviename=moviename;
		this.genre=genre;
		this.description=description;
		this.image_id=image_id;
		this.name = name;
	    this.type = type;
	    this.data = data;
	    this.moviestatus=movistatus;
	    this.language=language;
	}
	
	
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 	


	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getMoviestatus() {
		return moviestatus;
	}

	public void setMoviestatus(int moviestatus) {
		this.moviestatus = moviestatus;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getMoviename() {
		return moviename.toUpperCase();
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename.toUpperCase();
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

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	
	public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
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
