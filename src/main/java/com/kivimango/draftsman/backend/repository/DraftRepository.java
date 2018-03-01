package com.kivimango.draftsman.backend.repository;

import java.util.List;
import com.kivimango.draftsman.backend.domain.Draft;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public interface DraftRepository {
	
	List<Draft> findByPartNumber(String pn);

}
