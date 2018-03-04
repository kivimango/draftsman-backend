package com.kivimango.draftsman.backend.domain;

/**
 * Truly immutable Data Transfer Object to represent drafts in the presentation layer.
 * DTO classes must not contain sensitive information like user passwords, roles, etc.
 * DTO's are converted by the DraftConverter class in the service layer, and will be sent in the response.
 * 
 * @author kivimango
 * @since 0.1
 * @version 0.1
 * @see DraftConverter
 */

public final class DraftDTO {
	
	private final Integer id;
	private final String image;
	private final String partNumber;
	private final String version;
	private final Integer sheet;
	
	public DraftDTO(Integer id, String image, String partNumber, String version, Integer sheeŧ) {
		this.id = id;
		this.image = image;
		this.partNumber = partNumber;
		this.version = version;
		this.sheet = sheeŧ;
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

	public Integer getSheet() {
		return sheet;
	}

}
