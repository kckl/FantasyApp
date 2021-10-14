package test;

import model.League;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeagueTest {
    private League testLeague;
    Team team1 = new Team("team1");
    Team team2 = new Team("team2");
    Team team3 = new Team("team3");

    @BeforeEach
    public void setUp() {
        testLeague = new League("2k21 Basketball League", 2);
    }
    @Test
    public void testConstructor() {
        assertEquals(testLeague.getLeagueSize(), 2);
        assertEquals(testLeague.getLeagueName(), "2k21 Basketball League");
    }

    @Test
    public void testGetLeague() {
        testLeague.registerTeam(team1);
        testLeague.registerTeam(team2);
        assertEquals(testLeague.getTeam(0), team1);
    }

    @Test
    public void testGetTeamNames() {
        testLeague.registerTeam(team1);
        testLeague.registerTeam(team2);
        assertEquals(testLeague.getTeamNames().size(), 2);
    }

    @Test
    public void testChangeLeagueName() {
        assertEquals(testLeague.getLeagueName(), "2k21 Basketball League");
        testLeague.changeLeagueName("2k21 Bball League");
        assertEquals(testLeague.getLeagueName(), "2k21 Bball League");
    }

    @Test
    public void testChangeLeagueSize() {
        assertEquals(testLeague.getLeagueSize(), 2);
        testLeague.changeLeagueSize(3);
        assertEquals(testLeague.getLeagueSize(), 3);
    }

    @Test
    public void testAddTeamLeagueNotFull() {
        assertTrue(testLeague.registerTeam(team1));
    }

    @Test
    public void testAddTeamLeagueFull() {
        testLeague.registerTeam(team1);
        testLeague.registerTeam(team2);
        assertFalse(testLeague.registerTeam(team3));
    }

}