package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "property1")
public class Buken implements Serializable {
	/**
	 * ID
	 */
	@Id
	@Column(name = "property_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long propertyId;
	/**
	 * 物件名
	 */
	@Column(name = "property_name")
	private String propertyName;
	/**
	 * 住所
	 */
	@Column(name = "address")
	private String address;
	/**
	 * Type
	 */
	@Column(name = "property_type")
	private String propertyType;
	
	@Column(name = "period")
	private Date period;
	/**
	 * 面積
	 */
	@Column(name = "property_area")
	private String propertyArea;
	/**
	 * 賃料
	 */
	@Column(name = "price")
	private String price;
	/**
	 * 所属物件ID
	 */
	@Column(name = "syozoku_company_id")
	private String syozokuCompanyId;

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

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * 状態
	 */
	@Column(name = "status")
	private String status;
	/**
	 * 写真
	 */
//	@Column(name = "image")
//	private MultipartFile image;
	@Column(name = "imagePath")
	private String imagePath;

}