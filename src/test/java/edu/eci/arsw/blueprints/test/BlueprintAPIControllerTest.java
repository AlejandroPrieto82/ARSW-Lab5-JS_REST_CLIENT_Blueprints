package edu.eci.arsw.blueprints.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.blueprints.BlueprintApp;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BlueprintApp.class)
@AutoConfigureMockMvc
public class BlueprintAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BlueprintsServices blueprintServices;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void resetBlueprints() throws Exception {
        // Se puede limpiar los blueprints agregados en tests anteriores si fuera necesario.
        // Aquí asumimos que cada test se ejecuta de manera independiente
        // y que la base de datos inicial de InMemoryPersistence tiene los 3 blueprints.
    }

    @Test
    void testGetAllBlueprints() throws Exception {
        int total = blueprintServices.getAllBlueprints().size();

        mockMvc.perform(get("/blueprints"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.length()").value(total));
    }

    @Test
    void testGetBlueprintsByAuthor() throws Exception {
        int totalByJuan = blueprintServices.getBlueprintsByAuthor("juan").size();

        mockMvc.perform(get("/blueprints/juan"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.length()").value(totalByJuan));
    }

    @Test
    void testGetBlueprintByAuthorAndName() throws Exception {
        mockMvc.perform(get("/blueprints/juan/plano1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.author", is("juan")))
                .andExpect(jsonPath("$.name", is("plano1")));
    }

    @Test
    void testPostNewBlueprint() throws Exception {
        Blueprint newBp = new Blueprint("testAuthor", "bp1", new Point[]{new Point(5,5)});
        mockMvc.perform(post("/blueprints")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newBp)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/blueprints/testAuthor/bp1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.author", is("testAuthor")))
                .andExpect(jsonPath("$.name", is("bp1")));
    }

    @Test
    void testPutUpdateBlueprint() throws Exception {
        Blueprint updatedBp = new Blueprint("juan", "plano1", new Point[]{new Point(9,9)});
        mockMvc.perform(put("/blueprints/juan/plano1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedBp)))
                .andExpect(status().isAccepted());

        mockMvc.perform(get("/blueprints/juan/plano1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.points[0].x", is(9)))
                .andExpect(jsonPath("$.points[0].y", is(9)));
    }
}
