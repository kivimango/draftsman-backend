package com.kivimango.draftsman.backend.domain;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class DraftTest {
	
	protected final Integer id = 1;
	protected final String image = "https://localhost/drafts/123-4567.jpg";
	protected final String partNumber = "123-4567";
	protected final String version = "00";
	protected final Integer sheeŧ = 1;
	protected final Date uploaded = new Date(System.currentTimeMillis());
	protected final Integer uploadedBy = 16;
	protected final Date edited = new Date(System.currentTimeMillis());;
	protected final Integer editedBy = 16;
	
	private Draft draft;
	
	@Before
	public void init() {
		draft = new Draft.Builder().id(id).image(image).partNumber(partNumber).version(version).sheet(sheeŧ)
				.uploaded(uploaded).uploadedBy(uploadedBy).edited(edited).editedBy(editedBy).build();
	}
	
	@Test
	public void testDraft() {
		assertEquals(id, draft.getId());
		assertEquals(image, draft.getImage());
		assertEquals(partNumber, draft.getPartNumber());
		assertEquals(version, draft.getVersion());
		assertEquals(sheeŧ, draft.getSheeŧ());
		assertEquals(uploaded, draft.getUploaded());
		assertEquals(uploadedBy, draft.getUploadedBy());
		assertEquals(edited, draft.getEdited());
		assertEquals(editedBy, draft.getEditedBy());
	}

}
