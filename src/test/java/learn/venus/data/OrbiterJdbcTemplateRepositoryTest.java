package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrbiterJdbcTemplateRepositoryTest {

    OrbiterJdbcTemplateRepository repository;

    public OrbiterJdbcTemplateRepositoryTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        repository = context.getBean(OrbiterJdbcTemplateRepository.class);
    }

    @BeforeAll
    static void oneTimeSetup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        jdbcTemplate.update("call set_known_good_state();");
    }

    /*
    Overall Test Strategy:
    - Leave pet_id 1 alone.
    - Update pet_id 2.
    - Delete pet_id 3.
    Therefore, can't depend on pet 2 to remain the same and can't depend on pet 3 to exist.
    Also can't count pets because a pet may be added at any time.
     */

    // (`name`, `type`, sponsor)
    // ('Carmen','MODULE', 'Tesla'),
    @Test
    void shouldFindAll() {
        List<Orbiter> all = repository.findAll();
        assertNotNull(all);
        assertTrue(all.size() >= 2);

        Orbiter expected = new Orbiter();
        expected.setOrbiterId(1);
        expected.setName("Carmen");
        expected.setType(OrbiterType.MODULE);
        expected.setSponsor("Tesla");

        Orbiter expected2 = new Orbiter();
        expected2.setOrbiterId(1);
        expected2.setName("Carmen");
        expected2.setType(OrbiterType.MODULE);
        expected2.setSponsor("Tesla");

        assertEquals(expected, expected2);
        //assertEquals(expected.getOrbiterId(), 1);

        assertTrue(all.contains(expected) && all.stream()
                .anyMatch(i->i.getOrbiterId() == 1));

    }





}
