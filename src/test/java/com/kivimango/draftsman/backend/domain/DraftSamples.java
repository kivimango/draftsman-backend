package com.kivimango.draftsman.backend.domain;

import java.util.Date;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class DraftSamples {
	protected Integer id = 23;
	protected final String image = "https://localhost/drafts/123-4567.jpg";
	protected final String partNumber = "123-4567";
	protected final String version = "00";
	protected final Integer sheet = 1;
	protected final Date uploaded = new Date(System.currentTimeMillis());
	protected final Integer uploadedBy = 16;
	protected final Date edited = new Date(System.currentTimeMillis());;
	protected final Integer editedBy = 16;
	
	protected final Draft sample = new Draft.Builder().id(id).image(image).partNumber(partNumber)
			.version(version).sheet(sheet).uploaded(uploaded).uploadedBy(uploadedBy).edited(edited)
			.editedBy(editedBy).build();
}
