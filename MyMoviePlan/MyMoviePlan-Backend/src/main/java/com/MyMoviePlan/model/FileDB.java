package com.MyMoviePlan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class FileDB {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String name;
  private String type;
  @Lob
  private byte[] data;
  
  @Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	@CreationTimestamp
	private Date created_at;
	
	@PrePersist
	private void onCreate() {
		created_at=new Date();
		
		}
  public FileDB() {
  }
  public FileDB(String name, String type, byte[] data) {
	this.name = name;
    this.type = type;
    this.data = data;
  }
  
  public FileDB(String id,String name, String type, byte[] data) {
		this.id=id;
	    this.name = name;
	    this.type = type;
	    this.data = data;
	  }
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
	this.id = id;
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