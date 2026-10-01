import org.example.Slon;
import org.example.Slon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlonTest {
    @Test
    void teasesBackWithUsersOwnLine() {
        Slon slon = new Slon();
        String response = slon.respond("Привет");
        assertTrue(response.contains("\"Привет\""));
        assertTrue(response.contains("А ты купи слона!"));
    }
    @Test
    void helpCommandReturnsGreeting() {
        Slon slon = new Slon();
        assertEquals(slon.greeting(), slon.respond("\\help"));
    }

    @Test
    void exitCommandIsRecognized() {
        Slon slon = new Slon();
        assertTrue(slon.isExitCommand("\\exit"));
        assertFalse(slon.isExitCommand("Привет"));
    }
}

