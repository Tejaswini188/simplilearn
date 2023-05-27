package com.kitchenstory.models;

public class Products {
	private int product_id;
	private String product_name;
	private double MSRP;
	private String url;

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getMSRP() {
		return MSRP;
	}

	public void setMSRP(double mSRP) {
		MSRP = mSRP;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Products() {

	}

	public Products(int product_id, String product_name, double mSRP, String url) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		MSRP = mSRP;
		this.url = url;
	}

	@Override
	public String toString() {
		return "Products [product_id=" + product_id + ", product_name=" + product_name + ", MSRP=" + MSRP + ", url="
				+ url + "]";
	}
}
