package com.kivimango.draftsman.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kivimango.draftsman.backend.converter.DraftConverter;
import com.kivimango.draftsman.backend.domain.Draft;
import com.kivimango.draftsman.backend.domain.DraftDTO;
import com.kivimango.draftsman.backend.exception.DraftNotFoundException;
import com.kivimango.draftsman.backend.repository.DraftRepository;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Service
final class DraftServiceImpl implements DraftService {
	
	Logger logger = LoggerFactory.getLogger(DraftServiceImpl.class);
	
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

	@Override
	public DraftDTO findById(Integer id) throws DraftNotFoundException {
		if(id < 0) {
			logger.error("Invalid id param : {}", id);
			throw new IllegalArgumentException("ID must bew greater than 0 !");
		}
		Draft draft = repo.findByID(id);
		if(draft == null) throw new DraftNotFoundException(id);
		return converter.convert(draft);
	}

}
