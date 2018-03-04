package com.kivimango.draftsman.backend.repository;

import java.util.List;
import com.kivimango.draftsman.backend.domain.Draft;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface DraftRepository {
	
	/** 
	 * Searches the database for the given part number.It lists similar part numbers to the query, 
	 * or/and the exact part number given.
	 * @param partNumber
	 * @return A list containing the matching items, or an empty list if nothing found.
	*/
	
	List<Draft> findByPartNumber(String pn);

	/**
	 * Searches an item in the database for the given id.
	 * The search should find only one row on matching.
	 * @param id the unique id of the item
	 * @return the mapped row as Draft object, or null if no result
	 */
	
	Draft findByID(Integer id);

}
