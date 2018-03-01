package com.kivimango.draftsman.backend.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import com.kivimango.draftsman.backend.domain.Draft;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

@Repository
public class DraftDaoImpl implements DraftRepository {
	
	private JdbcOperations jdbc;
	private final DraftRowMapper rowMapper = new DraftRowMapper();

	@Autowired
	public DraftDaoImpl(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Draft> findByPartNumber(String pn) {
		String like = "%" + pn + "%";
		return jdbc.query("SELECT * FROM DR_DRAFTS WHERE part_number LIKE ? OR part_number = ?", new Object[]{like, pn}, rowMapper);
	}

}
