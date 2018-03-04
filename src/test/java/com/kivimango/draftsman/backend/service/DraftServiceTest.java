package com.kivimango.draftsman.backend.service;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.Mockito;
import com.kivimango.draftsman.backend.domain.Draft;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.domain.DraftSamples;
import com.kivimango.draftsman.backend.exception.DraftNotFoundException;
import com.kivimango.draftsman.backend.repository.DraftRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class DraftServiceTest extends DraftSamples {
	
	private DraftRepository repo = Mockito.mock(DraftRepository.class);
	private List<Draft> mockReturn = Arrays.asList(sample);
	private DraftService service = new DraftServiceImpl(repo);
	
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
	
	@Test
	public void testFindByPartNumberWhenDaoReturnsNullShouldReturnEmptyList() {
		Mockito.when(service.findByPartNumber(partNumber)).thenReturn(null);
		List<DraftDTO> result = service.findByPartNumber(partNumber);
		assertEquals(0, result.size());
	}
	
	@Test
	public void testFindById() throws DraftNotFoundException {
		Mockito.when(repo.findByID(id)).thenReturn(sample);
		
		DraftDTO actual = service.findById(id);
		assertEquals(id, actual.getId());
		assertEquals(image, actual.getImage());
		assertEquals(partNumber, actual.getPartNumber());
		assertEquals(version, actual.getVersion());
		assertEquals(sheet, actual.getSheet());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindByIdWithNegativeNumberShouldThrowException() throws DraftNotFoundException {
		service.findById(-1);
	}
	
	@Test(expected=DraftNotFoundException.class)
	public void testFindByIdWhenDaoReturnsNullShouldThrowException() throws DraftNotFoundException {
		Mockito.when(repo.findByID(id)).thenReturn(null);
		service.findById(id);
	}

}
