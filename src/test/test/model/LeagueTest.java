package test.model;

import model.League;
import model.Player;
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
        assertEquals(2, testLeague.getLeagueSize());
        assertEquals("2k21 Basketball League", testLeague.getLeagueName());
    }

    @Test
    public void testGetLeague() {
        testLeague.registerTeam(team1);
        testLeague.registerTeam(team2);
        assertEquals(team1, testLeague.getTeam(0));
    }

    @Test
    public void testGetTeamNames() {
        testLeague.registerTeam(team1);
        testLeague.registerTeam(team2);
        assertEquals(2, testLeague.getTeamNames().size());
    }

    @Test
    public void testChangeLeagueName() {
        assertEquals("2k21 Basketball League", testLeague.getLeagueName());
        testLeague.changeLeagueName("2k21 Bball League");
        assertEquals("2k21 Bball League", testLeague.getLeagueName());
    }

    @Test
    public void testChangeLeagueSize() {
        assertEquals(2, testLeague.getLeagueSize());
        testLeague.changeLeagueSize(3);
        assertEquals(3, testLeague.getLeagueSize());
    }

    @Test
    public void testAddTeamLeagueNotFull() {
        assertFalse(testLeague.isFull());
        assertTrue(testLeague.registerTeam(team1));
    }

    @Test
    public void testAddTeamLeagueFull() {
        testLeague.registerTeam(team1);
        testLeague.registerTeam(team2);
        assertTrue(testLeague.isFull());
        assertFalse(testLeague.registerTeam(team3));
    }

    @Test
    public void testAddTeamAlreadyInLeague() {
        assertTrue(testLeague.registerTeam(team1));
        assertFalse(testLeague.registerTeam(team1));
    }

    @Test
    public void testRemoveTeam() {
        testLeague.registerTeam(team1);
        testLeague.registerTeam(team2);
        assertTrue(testLeague.removeTeam("team1"));
        assertFalse(testLeague.getLeagueName().contains("team1"));
    }

    @Test
    public void testRemoveTeamNotInLeague() {
        testLeague.registerTeam(team2);
        assertFalse(testLeague.removeTeam("team1"));
    }

    @Test
    public void testRemoveTeamEmptyLeague() {
        assertFalse(testLeague.removeTeam("team1"));
    }

    @Test
    public void testLeagueHashCodeEquals() {
        Team t1 = new Team("team3");
        Team t2 = new Team("team3");
        assertTrue(testLeague.registerTeam(t1));
        assertFalse(testLeague.registerTeam(t2));
        assertTrue(t1.equals(t2) && t2.equals(t1));
        assertTrue(t1.hashCode() == t2.hashCode());
        assertNotEquals(t1, null);
        assertNotEquals(t2, null);
    }

    @Test
    public void testLeagueHashCodeNotEqual() {
        Team t1 = new Team("team3");
        Team t2 = new Team("team4");
        assertTrue(testLeague.registerTeam(t1));
        assertTrue(testLeague.registerTeam(t2));
        assertFalse(t1.equals(t2) && t2.equals(t1));
        assertFalse(t1.hashCode() == t2.hashCode());

    }
}