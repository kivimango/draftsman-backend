package com.kivimango.draftsman.backend.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.kivimango.draftsman.backend.domain.Draft;

/**
 * @author kivimango
 * @since 0.1
 * @version 0.1
 */

public class DraftRowMapper implements RowMapper<Draft> {

	@Override
	public Draft mapRow(ResultSet rs, int row) throws SQLException {
		Draft draft = new Draft.Builder().id(rs.getInt("dr_id"))
				.image(rs.getString("img_src"))
				.partNumber(rs.getString("part_number"))
				.version(rs.getString("version"))
				.sheet(rs.getInt("sheet"))
				.uploaded(rs.getDate("uploaded"))
				.uploadedBy(rs.getInt("uploadedBy"))
				.edited(rs.getDate("edited"))
				.editedBy(rs.getInt("editedBy"))
				.build();
		return draft;
	}

}
