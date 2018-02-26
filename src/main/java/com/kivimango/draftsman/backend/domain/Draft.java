package com.kivimango.draftsman.backend.domain;

import java.util.Date;

/**
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public final class Draft {
	
	private final Integer id;
	private final String image;
	private final String partNumber;
	private final String version;
	private final Integer sheeŧ;
	private final Date uploaded;
	private final Integer uploadedBy;
	private final Date edited;
	private final Integer editedBy;
	
	private Draft(Builder db) {
		this.id = db.id;
		this.image = db.image;
		this.partNumber = db.partNumber;
		this.version = db.version;
		this.sheeŧ = db.sheeŧ;
		this.uploaded = db.uploaded;
		this.uploadedBy = db.uploadedBy;
		this.edited = db.edited;
		this.editedBy = db.editedBy;
	}
	
	public Integer getId() {
		return id;
	}
	public String getImage() {
		return image;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public String getVersion() {
		return version;
	}
	public Integer getSheeŧ() {
		return sheeŧ;
	}
	public Date getUploaded() {
		return uploaded;
	}
	public Integer getUploadedBy() {
		return uploadedBy;
	}
	public Date getEdited() {
		return edited;
	}
	public Integer getEditedBy() {
		return editedBy;
	}
	
	/**
	 * Avoiding a long constructor with lots of parameters using the builder pattern.
	 */
	
	public static final class Builder {
		private Integer id;
		private String image;
		private String partNumber;
		private String version;
		private Integer sheeŧ;
		private Date uploaded;
		private Integer uploadedBy;
		private Date edited;
		private Integer editedBy;
		
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public Builder image(String src) {
			this.image = src;
			return this;
		}
		
		public Builder partNumber(String pn) {
			this.partNumber = pn;
			return this;
		}
		
		public Builder version(String vrs) {
			this.version = vrs;
			return this;
		}
		
		public Builder sheet(Integer sht) {
			this.sheeŧ = sht;
			return this;
		}
		
		public Builder uploaded(Date upl) {
			this.uploaded = upl;
			return this;
		}
		
		public Builder uploadedBy(Integer id) {
			this.uploadedBy = id;
			return this;
		}
		
		public Builder edited(Date edit) {
			this.edited = edit;
			return this;
		}
		
		public Builder editedBy(Integer id) {
			this.editedBy = id;
			return this;
		}
		
		public Draft build() {
			return new Draft(this);
		}
	}

}
