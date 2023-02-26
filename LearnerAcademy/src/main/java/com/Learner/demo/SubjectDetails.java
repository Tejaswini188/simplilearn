package com.Learner.demo;
import java.math.BigDecimal;

public class SubjectDetails {

	private long ID;
	private String name;
	private String std;
	private BigDecimal subcode;
	
	SubjectDetails(){
		
	}

	public SubjectDetails(String name, String std, BigDecimal subcode) {
		super();
		this.name = name;
		this.std = std;
		this.subcode = subcode;
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
	 * @return the std
	 */
	public String getStd() {
		return std;
	}

	/**
	 * @param std the std to set
	 */
	public void setStd(String std) {
		this.std = std;
	}

	/**
	 * @return the subcode
	 */
	public BigDecimal getSubcode() {
		return subcode;
	}

	/**
	 * @param subcode the subcode to set
	 */
	public void setSubcode(BigDecimal subcode) {
		this.subcode = subcode;
	}

	@Override
	public String toString() {
		return "SubjectDetails [ID=" + ID + ", name=" + name + ", std=" + std + ", subcode=" + subcode + "]";
	}
	
	
	
	
}
