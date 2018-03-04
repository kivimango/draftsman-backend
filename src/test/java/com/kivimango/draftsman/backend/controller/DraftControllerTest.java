package com.kivimango.draftsman.backend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
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
import com.kivimango.draftsman.backend.exception.DraftNotFoundException;
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
	
	@Value("${image.repository.path}")
	private String testRepoPath;
	
	@Value("classpath:sample.jpg")
	Resource drawing;
	
	@MockBean
	private DraftService service;
	
	private String partNumber = "123-4567";
	private List<DraftDTO> list = Arrays.asList(new DraftDTO(id, image, partNumber, version, sheet));
	
	@Test
	public void testFindByPartNumber() throws Exception {
		String URI = "/drafts/list/";
		MockHttpServletResponse response = buildRequestAndPerform(URI + partNumber); 
	
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, response.getContentType());
		assertThat(response.getContentAsString(), containsString(partNumber));
		verify(service).findByPartNumber(partNumber);
	}
	
	@Test
	public void testFindByPartNumberWithNoneExistingPartNumberShouldReturnEmptyListAndCode204() throws Exception {
		String URI = "/drafts/list/";
		String nonExistentPartNumber = "000-0000";
		List<DraftDTO> emptyList = new ArrayList<DraftDTO>(0);
		Mockito.when(service.findByPartNumber(partNumber)).thenReturn(emptyList);
		
		MockHttpServletResponse response = buildRequestAndPerform(URI + nonExistentPartNumber);
		String responseStr = response.getContentAsString();
	
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		assertTrue(responseStr.isEmpty());
		verify(service).findByPartNumber(nonExistentPartNumber);
	}
	
	@Test
	public void testFindByPartNumberWithParameterLessThanMinimal() throws Exception {
		String URI = "/drafts/list/12";	
		RequestBuilder rb = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
		MockHttpServletResponse response = mockMvc.perform(rb).andReturn().getResponse();
		
		assertEquals("{\"errorMessage\":\"Parameter partNumber length must be at least 3 character long\"}", response.getContentAsString());
	}
	
	@Test
	public void testViewDraftShouldReturnImageAndCode200() throws Exception {
		String URI = "/drafts/view/";
		DraftDTO draft = new DraftDTO(id, drawing.getFile().getAbsolutePath(), partNumber, version, sheet);
		Mockito.when(service.findById(id)).thenReturn(draft);
		
		RequestBuilder rb = MockMvcRequestBuilders.get(URI + id.toString()).accept(MediaType.IMAGE_JPEG_VALUE);
		MockHttpServletResponse response = mockMvc.perform(rb).andReturn().getResponse();
		byte[] body = response.getContentAsByteArray();
		
		// Reading the content of the sample picture
		RandomAccessFile file = new RandomAccessFile(drawing.getFile(), "r");
		byte[] actual = new byte[(int) file.length()];
		file.readFully(actual);
		file.close();
		
		assertTrue(Arrays.equals(actual, body));
	}
	
	@Test
	public void testViewDraftWithNonExistentParamShouldReturn404() throws Exception {
		String URI = "/drafts/view/";
		Mockito.when(service.findById(5)).thenThrow(new DraftNotFoundException(5));
		
		RequestBuilder rb = MockMvcRequestBuilders.get(URI + 5).accept(MediaType.IMAGE_JPEG_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		MockHttpServletResponse response = mockMvc.perform(rb).andReturn().getResponse();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, response.getContentType());
		assertEquals("{\"errorMessage\":\"The requested item (5) could nout be found !\"}", response.getContentAsString());
	}
	
	private MockHttpServletResponse buildRequestAndPerform(String uri) throws Exception {
		Mockito.when(service.findByPartNumber(partNumber)).thenReturn(list);
		RequestBuilder rb = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(rb).andReturn();
		MockHttpServletResponse response = result.getResponse();
		return response;
	}

}
