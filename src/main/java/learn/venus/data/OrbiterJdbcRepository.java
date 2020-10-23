package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Repository
@Profile("jdbc")
public class OrbiterJdbcRepository implements OrbiterRepository{
    private DataSource dataSource = initDataSource();

    private DataSource initDataSource() {
        MysqlDataSource result = new MysqlDataSource();
        // connection string is: [db-tech]:[db-vendor]://[host]:[port]/[database-name]
        result.setUrl("jdbc:mysql://localhost:3306/orbiters"); //jdbc:mysql://localhost:3306/pets
        result.setUser("root"); //your-username-here
        result.setPassword("top-secret-password");
        return result;
    }


    public List<Orbiter> findAll() {
        ArrayList<Orbiter> result = new ArrayList<>();

        final String sql = "select orbiter_id, `name`, `type`, sponsor from orbiter;";

        // 1. try-with-resources
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             // 2. a Statement executes a SQL query
             ResultSet rs = statement.executeQuery(sql)) {

            // 3. Process a row at a time until there are no more.
            while (rs.next()) {
                Orbiter orbiter = new Orbiter();
                // 4. Column values are for the current row.
                orbiter.setOrbiterId(rs.getInt("orbiter_id"));
                orbiter.setName(rs.getString("name"));
                orbiter.setType(OrbiterType.valueOf(rs.getString("type"))); //***
                orbiter.setSponsor(rs.getString("sponsor"));
                result.add(orbiter);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
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
//NOT DONE? - add, update, delete...