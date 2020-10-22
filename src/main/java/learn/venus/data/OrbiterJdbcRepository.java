package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrbiterJdbcRepository {
    private DataSource dataSource = initDataSource();

    private DataSource initDataSource() {
        MysqlDataSource result = new MysqlDataSource();
        // 2. connection string is:
        // [db-tech]:[db-vendor]://[host]:[port]/[database-name]
        result.setUrl("jdbc:mysql://localhost:3306/orbiters"); //jdbc:mysql://localhost:3306/pets
        // 3. username
        result.setUser("root"); //your-username-here
        // 4. password
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






}
