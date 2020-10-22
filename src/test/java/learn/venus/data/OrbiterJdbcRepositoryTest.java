package learn.venus.data;

import learn.venus.models.Orbiter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrbiterJdbcRepositoryTest {

    OrbiterJdbcRepository repository = new OrbiterJdbcRepository();

    @Test
    void should() {
        assertTrue (true);
    }

    @Test
    void shouldFindAll() {
        List<Orbiter> orbiters = repository.findAll();
        // Not really part of the test.
        // But it's nice to confirm in the console.
        System.out.println(orbiters);

        assertNotNull(orbiters);
        assertEquals(3, orbiters.size());
    }


}



