package com.Learner.demo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;

public class StudentDetails {

	private long ID;
	private String sname;
	private String fname;
	private String lname;
	private String gender;
	private String dob;
	private String doj;
	private String address;
	//@Column(name="class")
	private String clas;
	private BigDecimal fees;
	
	private List<StudentDetails> std; 
	
	public StudentDetails() {
		
	}
	
	public StudentDetails(String sname, String fname, String lname, String gender, String dob,
			String doj, String address, String clas, BigDecimal fees) {
		super();
		this.sname = sname;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.dob = dob;
		this.doj = doj;
		this.address = address;
		this.clas = clas;
		this.fees = fees;
	}




	/**
	 * @return the iD
	 */
	public long getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(long iD) {
		ID = iD;
	}

	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the doj
	 */
	public String getDoj() {
		return doj;
	}

	/**
	 * @param doj the doj to set
	 */
	public void setDoj(String doj) {
		this.doj = doj;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the clas
	 */
	public String getClas() {
		return clas;
	}

	/**
	 * @param clas the clas to set
	 */
	public void setClas(String clas) {
		this.clas = clas;
	}

	/**
	 * @return the fees
	 */
	public BigDecimal getFees() {
		return fees;
	}

	/**
	 * @param fees the fees to set
	 */
	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

	@Override
	public String toString() {
		return "StudentDetails [ID=" + ID + ", sname=" + sname + ", fname=" + fname + ", lname=" + lname + ", gender="
				+ gender + ", dob=" + dob + ", doj=" + doj + ", address=" + address + ", clas=" + clas + ", fees="
				+ fees + "]";
	}
	
	
}
