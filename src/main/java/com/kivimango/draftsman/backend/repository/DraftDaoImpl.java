package com.kivimango.draftsman.backend.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${image.repository.path}")
	private static String fileRepoPath;
	
	private final DraftRowMapper rowMapper;
	
	private JdbcOperations jdbc;
	
	@Autowired
	public DraftDaoImpl(JdbcOperations jdbc, @Value("${image.repository.path}") String path) {
		this.jdbc = jdbc;
		fileRepoPath = path;
		rowMapper = new DraftRowMapper(fileRepoPath);
	}

	@Override
	public List<Draft> findByPartNumber(String pn) {
		String like = "%" + pn + "%";
		return jdbc.query("SELECT dr_id, img_src, part_number, version, sheet FROM DR_DRAFTS WHERE part_number LIKE ? OR part_number = ?;", 
			(rs, i) -> 
				new Draft.Builder().id(rs.getInt(1))
				.image(fileRepoPath + rs.getString(2))
				.partNumber(rs.getString(3))
				.version(rs.getString(4))
				.sheet(rs.getInt(5))
				.build(), 
		like, like);
	}

	@Override
	public Draft findByID(Integer id) {
		return jdbc.query("SELECT * FROM DR_DRAFTS WHERE dr_id = ? LIMIT 1;", 
		rs -> rs.next() ? rowMapper.mapRow(rs, 1) : null, id);
	}

}
