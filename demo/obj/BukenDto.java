package com.example.demo.obj;

import java.sql.Date;


public class BukenDto {
	private Long propertyId;
	private String propertyName;
	private String address;
	private String propertyType;
	private Date period;
	private String propertyArea;
	private String price;
	private String syozokuCompanyId;
	private String status;
	private String imagePath;

	public BukenDto() {
	}

	public BukenDto(Long propertyId, String propertyName, String address, String propertyType, Date period, String propertyArea,
			String price, String syozokuCompanyId, String status, String imagePath) {
		this.propertyId = propertyId;
		this.propertyName = propertyName;
		this.address = address;
		this.propertyType = propertyType;
		this.period = period;
		this.propertyArea = propertyArea;
		this.price = price;
		this.syozokuCompanyId = syozokuCompanyId;
		this.status = status;
		this.imagePath = imagePath;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period; 
	}
	public String getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(String propertyArea) {
		this.propertyArea = propertyArea;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSyozokuCompanyId() {
		return syozokuCompanyId;
	}

	public void setSyozokuCompanyId(String syozokuCompanyId) {
		this.syozokuCompanyId = syozokuCompanyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePaht) {
		this.imagePath = imagePaht;
	}

}
