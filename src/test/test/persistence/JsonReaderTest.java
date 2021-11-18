package test.persistence;

import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from this repo
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFakeFile() {
        JsonReader reader = new JsonReader("./data/fakefile.json");
        try {
            League league = reader.read();
            fail("IOException not thrown!");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyLeague() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeague.json");
        try {
            League league = reader.read();
            assertEquals("NBA2k21League", league.getLeagueName());
            assertEquals(0, league.getTeamNames().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalLeagueWithTwoTeams() {
        JsonReader reader = new JsonReader("./data/testReaderNormalLeague.json");
        try {
            League league = reader.read();
            assertEquals("NBA2k21League", league.getLeagueName());
            assertEquals(12, league.getLeagueSize());
            Team team1 = new Team("team11");
            league.registerTeam(team1);
           assertEquals(2, league.getTeamNames().size());
            checkTeam("team11", league.getTeam(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

//    @Test
//    void testReaderTeamWithPlayer() {
//        JsonReader reader = new JsonReader("./data/testReaderNormalLeague.json");
//        try {
//            League league = reader.read();
//            Team team = league.getTeam(0);
//            Player player1 = new Player("Curry", team);
//            team.addPlayer(player1);
//            assertEquals(1, team.getPlayerNames().size());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}