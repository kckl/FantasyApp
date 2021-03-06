package test.persistence;

import model.Player;
import model.Team;
import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from this repo
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class JsonTest {
    protected void checkTeam(String name, Team team) {
        assertEquals(name, team.getTeamName());
    }

    protected void checkPlayer(String name, Team team, Player player) {
        assertEquals(name, player.getPlayerName());
        assertEquals(team, player.getFantasyTeam());
    }
}
