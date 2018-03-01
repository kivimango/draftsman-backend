package com.kivimango.draftsman.backend.service;

import java.util.List;
import com.kivimango.draftsman.backend.domain.DraftDTO;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface DraftService {
	
	/** 
	 * Searches the database for the given part number.It lists similar part numbers to the query, 
	 * or/and the exact part number given.
	 * Database entities must be converted to DTO objects.
	 * @param partNumber The part number the user is looking for
	 * @return The result of the search, or an empty list if nothing found.Returning null is not allowed !
	*/
	
	List<DraftDTO> findByPartNumber(String pn);

}
