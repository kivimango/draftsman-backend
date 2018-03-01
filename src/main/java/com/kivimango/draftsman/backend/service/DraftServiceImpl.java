package com.kivimango.draftsman.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.draftsman.backend.converter.DraftConverter;
import com.kivimango.draftsman.backend.domain.Draft;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.repository.DraftRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
final class DraftServiceImpl implements DraftService {
	
	private DraftRepository repo;
	private final DraftConverter converter = new DraftConverter();
	
	@Autowired
	public DraftServiceImpl(DraftRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<DraftDTO> findByPartNumber(String pn) {
		final List<Draft> result = repo.findByPartNumber(pn);
		if(result != null) return converter.convert(result); 
		else return new ArrayList<DraftDTO>(0);
	}

}
