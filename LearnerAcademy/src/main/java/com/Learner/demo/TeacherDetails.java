package com.Learner.demo;
import java.math.BigDecimal;

public class TeacherDetails {

	private long ID;
	private String name;
	private String email;
	private String gender;
	private String dob;
	private BigDecimal mobno;
	private String address;
	private String sub;
	
	TeacherDetails(){
		
	}

	public TeacherDetails(String name, String email, String gender, String dob, BigDecimal mobno, String address,
			String sub) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.mobno = mobno;
		this.address = address;
		this.sub = sub;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the mobno
	 */
	public BigDecimal getMobno() {
		return mobno;
	}

	/**
	 * @param mobno the mobno to set
	 */
	public void setMobno(BigDecimal mobno) {
		this.mobno = mobno;
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
	 * @return the sub
	 */
	public String getSub() {
		return sub;
	}

	/**
	 * @param sub the sub to set
	 */
	public void setSub(String sub) {
		this.sub = sub;
	}

	@Override
	public String toString() {
		return "TeacherDetails [ID=" + ID + ", name=" + name + ", email=" + email + ", gender=" + gender + ", dob="
				+ dob + ", mobno=" + mobno + ", address=" + address + ", sub=" + sub + "]";
	}
	
}
