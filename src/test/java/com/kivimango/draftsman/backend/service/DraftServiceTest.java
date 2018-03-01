package com.kivimango.draftsman.backend.service;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import com.kivimango.draftsman.backend.domain.Draft;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.domain.DraftSamples;
import com.kivimango.draftsman.backend.repository.DraftDaoImpl;
import com.kivimango.draftsman.backend.repository.DraftRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class DraftServiceTest extends DraftSamples {
	
	JdbcOperations jdbc = Mockito.mock(JdbcTemplate.class);
	DraftRepository repo = new DraftDaoImpl(jdbc);
	List<Draft> mockReturn = Arrays.asList(sample);
	DraftService service = new DraftServiceImpl(repo);
	
	@Test
	public void testFindByPartNumber() {
		Mockito.when(repo.findByPartNumber(partNumber)).thenReturn(mockReturn);
		List<DraftDTO> actual = service.findByPartNumber(partNumber);
		
		assertEquals(1, actual.size());
		assertEquals(id, actual.get(0).getId());
		assertEquals(partNumber, actual.get(0).getPartNumber());
		assertEquals(version, actual.get(0).getVersion());
		assertEquals(sheet, actual.get(0).getSheet());
	}

}
