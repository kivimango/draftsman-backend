package com.kivimango.draftsman.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.service.DraftService;

/**
 * @author sapka
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
	 * @param partNumber The part number the user is looking for
	 * @return The result of the search with code 200, or an empty list if nothing found with code 204.
	 */
	
	@GetMapping(value="/drafts/{partNumber}", produces={ MediaType.APPLICATION_JSON_VALUE} )
	ResponseEntity<List<DraftDTO>> listDrafts(@PathVariable(value="partNumber", required=true) final String partNumber) {
		final List<DraftDTO> results = drafts.findByPartNumber(partNumber);
		if(results.isEmpty()) {
			return new ResponseEntity<List<DraftDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DraftDTO>>(results, HttpStatus.OK);
	}

}
