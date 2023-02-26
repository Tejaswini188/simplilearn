package com.Learner.demo;

import java.math.BigDecimal;
import java.util.List;

public class ClassDetails {

	private long ID;
	private BigDecimal t_ID;
	private BigDecimal s_ID;
	private String std;
	private String time;
	private BigDecimal a_ID;
	private List<ClassDetails> cl_list;
	
	/**
	 * @return the cl_list
	 */
	public List<ClassDetails> getCl_list() {
		return cl_list;
	}

	/**
	 * @param cl_list the cl_list to set
	 */
	public void setCl_list(List<ClassDetails> cl_list) {
		this.cl_list = cl_list;
	}

	ClassDetails(){
		
	}

	public ClassDetails(BigDecimal t_ID, BigDecimal s_ID, String std, String time, BigDecimal a_ID) {
		super();
		this.t_ID = t_ID;
		this.s_ID = s_ID;
		this.std = std;
		this.time = time;
		this.a_ID = a_ID;
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
	 * @return the t_ID
	 */
	public BigDecimal getT_ID() {
		return t_ID;
	}

	/**
	 * @param t_ID the t_ID to set
	 */
	public void setT_ID(BigDecimal t_ID) {
		this.t_ID = t_ID;
	}

	/**
	 * @return the s_ID
	 */
	public BigDecimal getS_ID() {
		return s_ID;
	}

	/**
	 * @param s_ID the s_ID to set
	 */
	public void setS_ID(BigDecimal s_ID) {
		this.s_ID = s_ID;
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
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the a_ID
	 */
	public BigDecimal getA_ID() {
		return a_ID;
	}

	/**
	 * @param a_ID the a_ID to set
	 */
	public void setA_ID(BigDecimal a_ID) {
		this.a_ID = a_ID;
	}

	@Override
	public String toString() {
		return "ClassDetails [ID=" + ID + ", t_ID=" + t_ID + ", s_ID=" + s_ID + ", std=" + std + ", time=" + time
				+ ", a_ID=" + a_ID + "]";
	}

	
}
