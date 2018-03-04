package com.kivimango.draftsman.backend.controller;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.exception.DraftNotFoundException;
import com.kivimango.draftsman.backend.service.DraftService;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@RestController
class DraftController {
	
	private DraftService drafts;
	
	@Autowired
	public DraftController(DraftService drafts) {
		this.drafts = drafts;
	}

	/**
	 * Searches the database for the given part number.It lists similar part numbers to the query, 
	 * or/and the exact part number given.
	 * @param partNumber The draft the user is looking for
	 * @return The result of the search with code 200, or an empty list if nothing found with code 204.
	 */
	
	@GetMapping(value="/drafts/list/{partNumber}", produces={ MediaType.APPLICATION_JSON_VALUE} )
	ResponseEntity<List<DraftDTO>> listDrafts(@PathVariable(value="partNumber", required=true) final String partNumber) {
		if(partNumber == null || partNumber.isEmpty() || partNumber.length() < 3) throw new IllegalArgumentException("Parameter partNumber length must be at least 3 character long");
		final List<DraftDTO> results = drafts.findByPartNumber(partNumber);
		if(results.isEmpty()) {
			return new ResponseEntity<List<DraftDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DraftDTO>>(results, HttpStatus.OK);
	}
	
	/**
	 * Searches the database for a draft by the given id.
	 * NOTE: clients should accept both media types image/jpeg and application/json
	 * TODO: if "produces" info given in the annotation, spring will throw a HttpMediaTypeNotAcceptableException
	 * @param id The (datastore) id of the draft
	 * @return The image of the draft included in the response,
	 * or a json response with errorMessage property describing the error
	 * @throws DraftNotFoundException
	 * @throws IOException
	 */
	
	@GetMapping(value="/drafts/view/{id}")
	ResponseEntity<byte[]> viewDraft(@PathVariable(value="id", required=true) final Integer id) 
			throws DraftNotFoundException, IOException {
		final DraftDTO draft = drafts.findById(id);
		byte[] content = readPicture(draft);
		final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    headers.setContentLength(content.length);
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
	
	private byte[] readPicture(DraftDTO draft) throws IOException {
		final RandomAccessFile file = new RandomAccessFile(draft.getImage(), "r");
		byte[] content = new byte[(int) file.length()];
		file.readFully(content);
		file.close();
		return content;
	}

}
