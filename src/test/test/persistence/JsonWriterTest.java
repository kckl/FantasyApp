package test.persistence;

import model.League;
import model.Team;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from this repo
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
class JsonWriterTest extends JsonTest {


    @Test
    public void testWriterFakeFile() {
        try {
            League league = new League("NBA2k21League", 12);
            JsonWriter writer = new JsonWriter("./data/\0fakefile.json");
            writer.open();
            fail("IOException not thrown!");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testWriterEmptyLeague() {
        try {
            League league = new League("NBA2k21League", 12);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeague.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeague.json");
            league = reader.read();
            assertEquals("NBA2k21League", league.getLeagueName());
            assertEquals(12, league.getLeagueSize());
            assertEquals(0, league.getTeamNames().size());
        } catch (IOException e) {
            fail("IOException thrown but not expected.");
        }
    }

    @Test
    void testWriterNormalLeague() {
        try {
            League league = new League("NBA2k21League", 12);
            Team team1 = new Team("team1");
            league.registerTeam(team1);
            JsonWriter writer = new JsonWriter("./data/testWriterNormalLeague.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalLeague.json");
            league = reader.read();
            assertEquals("NBA2k21League", league.getLeagueName());
            assertEquals(1, league.getTeamNames().size());
            checkTeam("team1", league.getTeam(0));
        } catch (IOException e) {
            fail("IOException thrown but not expected.");
        }
    }
}