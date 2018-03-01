package com.kivimango.draftsman.backend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.domain.DraftSamples;
import com.kivimango.draftsman.backend.service.DraftService;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value=DraftController.class, secure=false)
public class DraftControllerTest extends DraftSamples {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DraftService service;
	
	private String partNumber = "123-4567";
	private String URI = "/drafts/";
	private List<DraftDTO> list = Arrays.asList(new DraftDTO(id, partNumber, version, sheet));
	
	@Test
	public void testFindByPartNumber() throws Exception {
		Mockito.when(service.findByPartNumber(partNumber)).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI + partNumber).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String responseStr = response.getContentAsString();
	
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, response.getContentType());
		assertThat(responseStr, containsString(partNumber));
		verify(service).findByPartNumber(partNumber);
	}
	
	@Test
	public void testFindByPartNumberWithNoneExistingPartNumber() throws Exception {
		String nonExistentPartNumber = "000-0000";
		List<DraftDTO> emptyList = new ArrayList<DraftDTO>(0);
		Mockito.when(service.findByPartNumber(partNumber)).thenReturn(emptyList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI+ nonExistentPartNumber).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
		String responseStr = response.getContentAsString();
	
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		assertTrue(responseStr.isEmpty());
		verify(service).findByPartNumber(nonExistentPartNumber);
	}

}
