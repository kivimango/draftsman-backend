package com.kivimango.draftsman.backend.converter;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import com.kivimango.draftsman.backend.domain.Draft;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.domain.DraftSamples;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public final class DraftConverterTest extends DraftSamples {
	
	private final DraftConverter converter = new DraftConverter();
	
	@Test
	public void testConvert() {
		DraftDTO result = converter.convert(sample);
		assertDraft(result);
	}
	
	@Test
	public void testConvertList() {
		List<Draft> list = Arrays.asList(sample, sample, sample);
		List<DraftDTO> result = converter.convert(list);
		assertEquals(3, result.size());
		assertDraft(result.get(0));
	}
	
	private void assertDraft(DraftDTO actual) {
		assertEquals(id, actual.getId());
		assertEquals(partNumber, actual.getPartNumber());
		assertEquals(version, actual.getVersion());
		assertEquals(sheet, actual.getSheet());
	}

}
