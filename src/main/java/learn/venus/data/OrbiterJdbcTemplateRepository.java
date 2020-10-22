package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrbiterJdbcTemplateRepository implements OrbiterRepository {
    private final JdbcTemplate jdbcTemplate;


    private final RowMapper<Orbiter> mapper = (resultSet, rowNum) -> {
        Orbiter orbiter = new Orbiter();
        orbiter.setOrbiterId(resultSet.getInt("orbiter_id"));
        orbiter.setName(resultSet.getString("name"));
        orbiter.setType(OrbiterType.valueOf(resultSet.getString("type")));  //***
        orbiter.setSponsor(resultSet.getString("sponsor"));
        return orbiter;
    };

    public OrbiterJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Orbiter> findAll() {
        final String sql = "select orbiter_id, `name`, `type`, sponsor from orbiter;";
        return jdbcTemplate.query(sql, mapper);

    }

    @Override
    public Orbiter findById(int orbiterId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Orbiter> findByType(OrbiterType type) throws DataAccessException {
        return null;
    }

    @Override
    public Orbiter add(Orbiter orbiter) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(Orbiter orbiter) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteById(int orbiterId) throws DataAccessException {
        return false;
    }
}
