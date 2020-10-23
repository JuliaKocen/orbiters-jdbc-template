package learn.venus.domain;

import learn.venus.data.DataAccessException;
import learn.venus.data.OrbiterRepository;
import learn.venus.data.OrbiterRepositoryDouble;
import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrbiterServiceTest {

    //OrbiterService service = new OrbiterService(new OrbiterRepositoryDouble());

//    @Test
//    void shouldFindByType() throws DataAccessException {
//        List<Orbiter> astronauts = service.findByType(OrbiterType.ASTRONAUT);
//        assertNotNull(astronauts);
//        assertEquals(2, astronauts.size());
//
//    }
//
//    @Test
//    void shouldNotAddNullOrbiter() throws DataAccessException {
//        OrbiterResult result = service.add(null);
//        assertFalse(result.isSuccess());
//    }
//    @Test
//    void shouldNotAddAstronautWithRoom() throws DataAccessException{
//        OrbiterResult result = service.add(
//                new Orbiter(0, "Test Astro", OrbiterType.ASTRONAUT, null));
//        assertFalse(result.isSuccess());
//    }
//    @Test
//    void shouldBeAbleToAddAstronaut() throws DataAccessException {
//        service.add(new Orbiter(0, "Test Mod", OrbiterType.MODULE, null));
//        OrbiterResult result = service.add(
//                new Orbiter(0, "Test Astro", OrbiterType.ASTRONAUT, null));
//        assertTrue(result.isSuccess());
//        assertNotNull(result.getPayload());
//    }
//    @Test
//    void shouldNotAddShuttleWithNoRoom() throws DataAccessException {
//        OrbiterResult result = service.add(
//                new Orbiter(0, "Test Shuttle", OrbiterType.SHUTTLE, null));
//        assertFalse(result.isSuccess());
//
//    }
//    @Test
//    void shouldAddShuttle() throws DataAccessException {
//        Orbiter orbiter = new Orbiter(0, "Test Dock", OrbiterType.MODULE_WITH_DOCK, null);
//        OrbiterResult result = service.add(orbiter);
//        assertTrue(result.isSuccess());
//    }
////orbiters.add(new Orbiter(3, "Astro #2", OrbiterType.ASTRONAUT, null));
//    @Test
//    void shouldUpdate() throws DataAccessException {
//        Orbiter orbiter = new Orbiter(3, "Updated Astro", OrbiterType.ASTRONAUT, null);
//        OrbiterResult result = service.update( orbiter);
//        assertTrue(result.isSuccess());
//    }
//
//    @Test
//    void shouldNotUpdateType() throws DataAccessException {
//        OrbiterResult result = service.update(
//                new Orbiter(3, "Updated Astro", OrbiterType.VENUSIAN, null));
//        assertFalse(result.isSuccess());
//    }
//
//    @Test
//    void shouldNotUpdateEmptyName() throws DataAccessException {
//        OrbiterResult result = service.update(
//                new Orbiter(3, "       ", OrbiterType.ASTRONAUT, null));
//        assertFalse(result.isSuccess());
//    }
//
//    @Test
//    void shouldDelete() throws DataAccessException {
//        OrbiterResult result = service.deleteById(3);
//        assertTrue(result.isSuccess());
//    }
//
//    @Test
//    void shouldNotDelete() throws DataAccessException {
//        OrbiterResult result = service.deleteById(1);
//        assertFalse(result.isSuccess());
//    }

        // 2. Mock the interface. Behavior is added per test.
        @MockBean
        OrbiterRepository repository;

        // 3. The mock PetRepository is injected into PetService
        // and PetService is injected into PetServiceTest.
        @Autowired
        OrbiterService service;

        @Test
        void shouldAdd() throws DataAccessException {
            // Arrange
            Orbiter orbiterIn = new Orbiter(0, "Charles", OrbiterType.ASTRONAUT, "Dev10");
            Orbiter orbiterOut = new Orbiter(1, "Charles", OrbiterType.ASTRONAUT, "Dev10");

            // 4. Stub a specific behavior.
            when(repository.add(orbiterIn)).thenReturn(orbiterOut);

            // Act
            OrbiterResult<Orbiter> result = service.add(orbiterIn);

            // Assert
            assertEquals(ResultType.SUCCESS, result.getType());
            assertEquals(orbiterOut, result.getPayload());
        }

        @Test
        void shouldNotAddNull() throws DataAccessException {
            OrbiterResult<Orbiter> result = service.add(null);
            assertEquals(ResultType.INVALID, result.getType());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotAddEmptyName() throws DataAccessException {
            Orbiter orbiterIn = new Orbiter(0, "Charles", OrbiterType.ASTRONAUT, "Dev10");
            OrbiterResult<Orbiter> result = service.add(orbiterIn);
            assertEquals(ResultType.INVALID, result.getType());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotDeleteMissingPet() throws DataAccessException {
            when(repository.deleteById(10)).thenReturn(false);
            assertFalse(service.deleteById(10));
        }

        @Test
        void shouldDelete() throws DataAccessException {
            when(repository.deleteById(2)).thenReturn(true);
            assertTrue(service.deleteById(2));
        }
}

//Done