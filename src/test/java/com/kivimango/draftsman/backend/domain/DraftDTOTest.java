package com.kivimango.draftsman.backend.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class DraftDTOTest {
	
	protected final Integer id = 23;
	protected final String image = "/home/draftsman/sample.jpg";
	protected final String partNumber = "123-4567";
	protected final String version = "00";
	protected final Integer sheet = 1;
	
	private DraftDTO draftDTO = new DraftDTO(id, image, partNumber, version, sheet);
	
	@Test
	public void testDraftDTO() {
		assertEquals(id, draftDTO.getId());
		assertEquals(partNumber, draftDTO.getPartNumber());
		assertEquals(version, draftDTO.getVersion());
		assertEquals(sheet, draftDTO.getSheet());
	}

}
