package com.MyMoviePlan.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="show_nms")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate  date;
	
	 @JsonFormat(pattern = "hh:mm:ss a")
	  private LocalTime time;
	
	private BigDecimal price;
	
	private String seatstatus;
	
	private String location;
		
	private String city;
	
	private String theater;
	
	private String screen;
	
	private String country;
	
	private String showname;
	
	private String duration;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	@CreationTimestamp
	private Date created_at;
	
	@PrePersist
	private void onCreate() {
		created_at=new Date();
		
		}
	
	/*
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	private Movie movie;*/

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
	
	/*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "show_movie",
        joinColumns = @JoinColumn(name = "show_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    
    private Set<Movie> movie = new HashSet<>();*/
    
    
	
	public String getShowname() {
		return showname;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country.toUpperCase();
	}

	public void setCountry(String country) {
		this.country = country.toUpperCase();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSeatstatus() {
		return seatstatus;
	}

	public void setSeatstatus(String seatstatus) {
		this.seatstatus = seatstatus;
	}
	
	public int compareTo(Show s) {
		if(s.getDate().isAfter(date))return -1;
		else if(s.getDate().isBefore(date))return 1;
		return 0;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}





}
