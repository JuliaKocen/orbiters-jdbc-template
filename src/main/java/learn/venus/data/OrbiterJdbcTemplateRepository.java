package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import org.springframework.context.annotation.Profile;
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
@Profile("jdbc-template") // NEW ANNOTATION
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
        final String sql = "insert into orbiter (`name`, `type`, sponsor) values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, orbiter.getName());
            ps.setString(2, String.valueOf(orbiter.getType()));
            ps.setString(3, orbiter.getSponsor());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        orbiter.setOrbiterId(keyHolder.getKey().intValue());
        return orbiter;
    }

    @Override
    public boolean update(Orbiter orbiter) throws DataAccessException {
        final String sql = "update orbiter set "
                + "`name` = ?, "
                + "`type` = ?, "
                + "sponsor = ? "
                + "where orbiter_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                orbiter.getName(), orbiter.getType(), orbiter.getSponsor(), orbiter.getOrbiterId()); //FIX

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int orbiterId) throws DataAccessException {
        final String sql = "delete from orbiter where orbiter_id = ?;";
        return jdbcTemplate.update(sql, orbiterId) > 0;
    }
}
