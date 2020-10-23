package learn.venus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.venus.data.OrbiterRepository;
import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest             // 1. Without an argument, @SpringBootTest creates a mock MVC environment.
@AutoConfigureMockMvc       // 2. Configure the mock MVC environment.
public class OrbiterControllerTest {

                // 3. Mock PetRepository with Mockito.
                // This ensure we don't need to worry about true data access.
    @MockBean
    OrbiterRepository repository;

                // 4. Create a field for mock MVC and let Spring Boot inject it.
    @Autowired
    MockMvc mvc;

    @Test
    void shouldGetAll() throws Exception {

        List<Orbiter> orbiters = List.of(
                new Orbiter(1, "Name #1", OrbiterType.ASTRONAUT, "Sponsor #1"),
                new Orbiter(2, "Name #2", OrbiterType.ASTRONAUT, "Sponsor #2"),
                new Orbiter(1, "Name #3", OrbiterType.ASTRONAUT, "Sponsor #3")
        );

                    // 5. ObjectMapper is the default JSON serializer for Spring MVC.
                    // We use it to generate the expected HTTP response body.
        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(orbiters);

                    // 6. Configure the per-test behavior for mock PetRepository.
        when(repository.findAll()).thenReturn(orbiters);

                    // 7. Send a mock HTTP request and assert facts about the response.
        mvc.perform(get("/orbiters"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}

//DONE- But not working