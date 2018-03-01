package com.kivimango.draftsman.backend.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.kivimango.draftsman.backend.domain.Draft;
import com.kivimango.draftsman.backend.domain.DraftDTO;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public final class DraftConverter {
	
	/**
	 * Converts a list of Drafts to the list of DraftDTOs.Null references are skipped.
	 * @param the list to convert
	 * @return the immutable converted list
	 * @see Draft
	 * @see DraftDTO
	 */
	
	public List<DraftDTO> convert(final List<Draft> drafts) {
		final List<DraftDTO> converted = new ArrayList<DraftDTO>();
		if( drafts != null) {
			for(int i=0; i<drafts.size(); i++) {
				Draft item = drafts.get(i);
				if(item != null) {
					converted.add(convert(item));
				}
			}
		}
		return Collections.unmodifiableList(converted);
	}
	
	/**
	 * Converts a Draft object to DraftDTO.
	 * @param the object to be converted
	 * @return the converted object
	 * @see Draft
	 * @see DraftDTO
	 */
	
	public DraftDTO convert(final Draft draft) {
		final DraftDTO dto = new DraftDTO(draft.getId(), draft.getPartNumber(), draft.getVersion(), draft.getSheeŧ());
		return dto;
	}

}
